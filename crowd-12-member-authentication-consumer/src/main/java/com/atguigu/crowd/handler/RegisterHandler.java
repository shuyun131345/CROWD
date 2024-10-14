package com.atguigu.crowd.handler;

import com.atguigu.crowd.api.MySQLRemoteService;
import com.atguigu.crowd.api.RedisRemoteService;
import com.atguigu.crowd.constant.CrowdConstant;
import com.atguigu.crowd.entity.ResultEntity;
import com.atguigu.crowd.entity.po.MemberPo;
import com.atguigu.crowd.entity.vo.MemberVo;
import com.atguigu.crowd.util.CrowdUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Controller
public class RegisterHandler {
    private Logger logger = LoggerFactory.getLogger(RegisterHandler.class);

    /**
     * 从首页的"注册"按钮，跳转到注册页面，不使用，使用配置类的注解方式跳转
     *
     * @return
     */
//    @RequestMapping("/auth/member/register.html")
//    public String toMemberRegister(){
//        return "register/member-register";
//
//    }

    @Autowired
    private RedisRemoteService redisRemoteService;

    @Autowired
    private MySQLRemoteService mySQLRemoteService;

    @Value("${message.host}")
    private String host;

    @Value("${message.path}")
    private String path;

    @Value("${message.method}")
    private String method;

    @Value("${message.appcode}")
    private String appcode;


    @Value("${message.smsSignId}")
    private String smsSignId;

    @Value("${message.templateId}")
    private String templateId;

    /**
     * 发送短信验证码到用户手机
     * @param phoneNum
     * @return
     */
    @ResponseBody
    @RequestMapping("/auth/member/sendShortMessage")
    public ResultEntity<String> sendMessage(@RequestParam("phoneNum") String phoneNum) {
        //调用第三方接口，发送短信验证码到用户手机上
        ResultEntity<String> sendShortMessageEntity = CrowdUtil.sendShortMessage(host, path, method, appcode, phoneNum, smsSignId, templateId);

        if (ResultEntity.SUCCESS.equals(sendShortMessageEntity.getResult())) {
            logger.info("短信发送成功");
            //短信发送成功
            //验证码存入redis中的key,确保每个号码唯一
            String key = CrowdConstant.REDIS_MESSAGE_CODE_PREFIX + phoneNum;
            //验证码
            String value = sendShortMessageEntity.getData();

            //短信发送成功，将验证码存入到redis中
            ResultEntity<String> redisstringResultEntity = redisRemoteService.setRedisKeyValueRemoteWithTimeout(key, value, 5, TimeUnit.MINUTES);

            logger.info("redis操作："+redisstringResultEntity.getResult());
            //不管redis成功失败，直接返回即可
            return redisstringResultEntity;
        } else {
            logger.info("短信发送失败");
            //短信发送失败，直接将结果返回前端
            return sendShortMessageEntity;
        }
    }

    /**
     * 执行会员注册
     * @param memberVo
     * @param modelMap
     * @return
     */
    @RequestMapping("/auth/member/do/register")
    public String memberRegister(@RequestBody MemberVo memberVo, ModelMap modelMap){
        String registerView = "/register/member-register";
        //1.VO参数检查，必要参数是否为空
        String tips = checkReq(memberVo,modelMap);
        if (!StringUtils.isEmpty(tips)){
            modelMap.addAttribute(CrowdConstant.REGISTER_CHECK,tips);
            return registerView;
        }

        //2.从redis中拿出验证码
        String messageCodeKey = CrowdConstant.REDIS_MESSAGE_CODE_PREFIX+memberVo.getPhoneNum();
        ResultEntity<String> messageCodeEntity = redisRemoteService.getRedisValueByKeyRemote(messageCodeKey);
        //redis请求失败，返回注册页面，提示错误消息
        if (ResultEntity.FAILED.equals(messageCodeEntity.getResult())){
            modelMap.addAttribute(CrowdConstant.REGISTER_CHECK,CrowdConstant.REGISTER_FAIL);
            return registerView;
        }

        //3.比较表单的验证码和redis中拿出的验证码是否一致
        String userCode = memberVo.getMessageCode();
        String redisCode = messageCodeEntity.getData();
        //验证码不一致
        if (!Objects.equals(userCode, redisCode)){
            modelMap.addAttribute(CrowdConstant.REGISTER_CHECK,CrowdConstant.MESSAGE_CODE_ERROR);
            return registerView;
        }

        //4.检查账号是否存在
        ResultEntity<MemberPo> memberEntity = mySQLRemoteService.getMemberByLoginacctRemote(memberVo.getLoginacct());
        if (!Objects.isNull(memberEntity.getData())){
            modelMap.addAttribute(CrowdConstant.REGISTER_CHECK,CrowdConstant.LOGINACCT_IS_EXIST);
            return registerView;
        }

        //5.密码加密
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPwd = encoder.encode(memberVo.getUserpswd());
        memberVo.setUserpswd(encodedPwd);

        //6.执行保存
        MemberPo memberPo = new MemberPo();
        BeanUtils.copyProperties(memberVo,memberPo);
        ResultEntity<String> saveMemberPoEntity = mySQLRemoteService.saveMemberPoRemote(memberPo);
        //mysql工程插入失败，返回注册页面，提示错误消息
        if (ResultEntity.FAILED.equals(saveMemberPoEntity.getResult())){
            modelMap.addAttribute(CrowdConstant.REGISTER_CHECK,CrowdConstant.REGISTER_FAIL);
            return registerView;
        }

        //7.保存成功后，删除redis中的验证码
        redisRemoteService.removeRedisKeyRemote(messageCodeKey);

        //8.重定向到登录页面，重定向的地址在配置类里面转发
        return "login/member-login";
    }

    /**
     * VO参数检查
     * @return
     */
    private String checkReq(MemberVo memberVo, ModelMap modelMap) {
        //检查不通过，返回原来注册页面，提示错误消息
        if (Objects.isNull(memberVo)){
            return CrowdConstant.REQUEST_IS_EMPTY;
        }
        if (StringUtils.isEmpty(memberVo.getLoginacct())){
            return CrowdConstant.LOGINACCT_IS_EMPTY;
        }
        if (StringUtils.isEmpty(memberVo.getUserpswd())){
            return CrowdConstant.PASD_IS_EMPTY;
        }
        if (StringUtils.isEmpty(memberVo.getPhoneNum())){
            return CrowdConstant.PHONENUM_IS_EMPTY;
        }
        if (StringUtils.isEmpty(memberVo.getMessageCode())){
            return CrowdConstant.MESSAGE_CODE_IS_EMPTY;
        }
        return null;
    }

}
