package com.atguigu.crowd.handler;

import com.atguigu.crowd.entity.ResultEntity;
import com.atguigu.crowd.entity.po.MemberPo;
import com.atguigu.crowd.service.api.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberProviderHandler {
    @Autowired
    private MemberService memberService;


    /**
     * provider工程的远程方法，要和api工程有 @FeignClient 注解标记的方法一致
     * 根据账号查询成员信息
     * @param loginacct
     * @return
     */
    @RequestMapping("/get/member/by/loginacct/remote")
    public ResultEntity<MemberPo> getMemberByLoginacctRemote(@RequestParam("loginacct") String loginacct){
        //todo 异常处理
        MemberPo member = memberService.getMemberByLoginacct(loginacct);
        return ResultEntity.successWithData(member);
    }

    /**
     * 新增会员
     * @param po
     * @return
     */
    @ResponseBody
    @RequestMapping("/mysql/save/member")
    public ResultEntity<String> saveMemberPoRemote(MemberPo po){
        return memberService.saveMemberPo(po);
    }


}
