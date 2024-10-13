package com.atguigu.crowd.service.impl;

import com.atguigu.crowd.entity.ResultEntity;
import com.atguigu.crowd.entity.po.MemberPo;
import com.atguigu.crowd.mapper.MemberPoMapper;
import com.atguigu.crowd.service.api.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

// 在类上使用@Transactional(readOnly = true)针对查询操作设置事务属性
//@Transactional(readOnly = true)
@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberPoMapper mapper;

    @Override
    public MemberPo getMemberByLoginacct(String loginacct) {
        return mapper.selectByLoginAcct(loginacct);
    }

    @Override
    public ResultEntity<String> saveMemberPo(MemberPo po) {
        try {
            mapper.saveMemberPo(po);
        }catch (Exception e){
            if (e instanceof DuplicateKeyException){
                return ResultEntity.failed("该账号已存在");
            }
            return ResultEntity.failed(e.getMessage());
        }
        return ResultEntity.successWithoutData();
    }
}
