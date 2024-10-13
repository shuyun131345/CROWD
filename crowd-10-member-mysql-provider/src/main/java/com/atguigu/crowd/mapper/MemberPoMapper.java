package com.atguigu.crowd.mapper;


import com.atguigu.crowd.entity.po.MemberPo;
import org.apache.ibatis.annotations.Param;

public interface MemberPoMapper {

    /**
     * 根据id查询成员信息
     * @param id
     * @return
     */
    MemberPo selectByPrimaryKey(Integer id);

    /**
     * 根据登录账号查询成员信息
     * @param loginAcct
     * @return
     */
    MemberPo selectByLoginAcct(@Param("loginAcct") String loginAcct);

    /**
     * 新增会员
     * @param po
     */
    void saveMemberPo(MemberPo po);
}