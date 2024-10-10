package com.atguigu.crowd.service.api;

import com.atguigu.crowd.entity.po.MemberPo;

public interface MemberService {

   MemberPo getMemberByLoginacct(String loginacct);
}
