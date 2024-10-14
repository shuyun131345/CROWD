package com.atguigu.crowd.service.impl;

import com.atguigu.crowd.entity.po.MemberPo;
import com.atguigu.crowd.mapper.MemberPoMapper;
import com.atguigu.crowd.service.api.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

// 在类上使用@Transactional(readOnly = true)针对查询操作设置事务属性
@Transactional(readOnly = true)
@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberPoMapper mapper;

    @Override
    public MemberPo getMemberByLoginacct(String loginacct) {
        return mapper.selectByLoginAcct(loginacct);
    }


    /**
     * REQUIRES_NEW 表示不管当前线程上有没有事务，都要自己开事务，在自己的事务中运行。
     * rollbackFor 编译时异常和运行时异常都回滚
     * readOnly 是否只读
     * @param po
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = Exception.class,readOnly = false)
    @Override
    public void saveMemberPo(MemberPo po) {
        mapper.saveMemberPo(po);
    }
}
