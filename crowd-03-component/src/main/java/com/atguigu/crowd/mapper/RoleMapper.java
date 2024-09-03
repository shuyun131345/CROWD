package com.atguigu.crowd.mapper;

import com.atguigu.crowd.entity.Role;
import com.atguigu.crowd.entity.RoleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {


    /**
     * 根据关键字查询角色信息
     * @param keyword
     * @return
     */
    List<Role> getRoleListByKeyword(@Param("keyword") String keyword);


    /**
     * 新增角色信息
     * @param roleName
     * @return
     */
    int saveRole(@Param("roleName") String roleName);

    /**
     * 根据id更新角色
     * @param role
     * @return
     */
    int updateRoleById(Role role);

    /**
     * 删除角色
     * @param roleList
     */
    void deleteRoles(@Param("roles") List<Role> roleList);


    /**
     * 根据adminId查询用户已拥有角色
     * @param adminId
     * @return
     */
    List<Role> selectAssignRolesByAdminId(@Param("adminId") Integer adminId);

    /**
     * 根据adminId查询用户未拥有角色
     * @param adminId
     * @return
     */
    List<Role> selectUnAssignRolesByAdminId(@Param("adminId") Integer adminId);


    int countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);



}