package com.atguigu.crowd.mapper;

import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.entity.AdminExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminMapper {

    /**
     * 根据账号查询管理员信息
     *
     * @param acount
     * @return
     */
    Admin selectByAcount(@Param("acount") String acount);

    /**
     * 按关键字查询管理员信息
     * @return
     */
    List<Admin> selectAdminListByKeyWord(@Param("keyWord") String keyWord);

    /**
     * 根据id删除管理员
     * @param id
     * @return
     */
    int deleteAdminById(@Param("id") Integer id);


    /**
     * 新增管理员信息
     * @param admin
     * @return
     */
    int insertAdmin(Admin admin);











    //==========逆向工程============

    int countByExample(AdminExample example);

    int deleteByExample(AdminExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    List<Admin> selectByExample(AdminExample example);

    Admin selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByExample(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);



}