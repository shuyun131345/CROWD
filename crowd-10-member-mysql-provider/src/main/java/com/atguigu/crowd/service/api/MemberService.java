package com.atguigu.crowd.service.api;

import com.atguigu.crowd.entity.po.MemberPo;

public interface MemberService {

    /**
     * 按账号查询会员信息
     * @param loginacct
     * @return
     */
   MemberPo getMemberByLoginacct(String loginacct);

    /**
     * 新增会员
     * @param po
     */
    void saveMemberPo(MemberPo po);
}
