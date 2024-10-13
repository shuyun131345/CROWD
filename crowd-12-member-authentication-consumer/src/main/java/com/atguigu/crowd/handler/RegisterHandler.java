package com.atguigu.crowd.handler;

import com.atguigu.crowd.api.RedisRemoteService;
import com.atguigu.crowd.constant.CrowdConstant;
import com.atguigu.crowd.entity.ResultEntity;
import com.atguigu.crowd.util.CrowdUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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


}
