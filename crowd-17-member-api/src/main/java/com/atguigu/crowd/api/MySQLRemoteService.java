package com.atguigu.crowd.api;

import com.atguigu.crowd.entity.ResultEntity;
import com.atguigu.crowd.entity.po.MemberPo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//@FeignClient注解表示和一个provider对应，value属性指定要调用的Provider的微服务名称;
// fallbackFactory属性指定 consumer 调用 provider 时如果失败所采取的备用方案
@FeignClient(value = "mysql-provider")
public interface MySQLRemoteService {

    /**
     * 根据账号查询会员信息
     * 会调用远程mysql-Provider工程的同名方法
     * 注意：这里的方法要和 Provider中的具体实现的方法的声明要一致
     * @RequestMapping注解的url地址、@RequestParam、@RequestBody、@PathVariable两边一致
     * @param loginacct
     * @return
     */
    @RequestMapping("/get/member/by/loginacct/remote")
    ResultEntity<MemberPo> getMemberByLoginacctRemote(@RequestParam("loginacct") String loginacct);

    /**
     * 新增会员
     * @param po
     * @return
     */
    @ResponseBody
    @RequestMapping("/mysql/save/member/remote")
    ResultEntity<String> saveMemberPoRemote(@RequestBody MemberPo po);
}
