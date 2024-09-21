package com.atguigu.crowd.mapper;

import com.atguigu.crowd.entity.Auth;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author shuyun
 * @date 2024-09-05 09-18-51
 */
public interface AuthMapper {

    /**
     * 查询所有权限信息
     * @return
     */
    List<Auth> selectAuthList();

    /**
     * 根据角色id查询已有权限信息
     * roleId 角色id
     * @return
     */
    List<Auth> selectAssignAuthList(@Param("roleId") Integer roleId);

    /**
     * 分配权限
     * @param authList
     * @param roleId
     */
    void saveAssignAuth(@Param("roleId") Integer roleId,@Param("list") List<Integer> authList);

    /**
     * 根据角色id删除已有权限
     * @param roleId
     */
    void deleteAuthByRoleId(@Param("roleId") Integer roleId);

    /**
     * 根据菜单id获取权限信息
     * @param id
     * @return
     */
    List<Auth> getMenuAssignAuthList(@Param("menuId") Integer id);

    /**
     * 根据菜单id删除已有权限
     * @param menuId
     */
    void deleteAuthByMenuId(@Param("menuId") Integer menuId);

    /**
     * 分配菜单权限
     * @param menuId
     * @param authList
     */
    void saveAssignMenuAuth(@Param("menuId") Integer menuId, @Param("list") List<Integer> authList);

    /**
     * 根据adminId查询已分配的权限名称列表
     * @param adminId
     * @return
     */
    List<String> selectAuthNameByAdminId(@Param("adminId") Integer adminId);
}
