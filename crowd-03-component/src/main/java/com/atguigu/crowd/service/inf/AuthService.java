package com.atguigu.crowd.service.inf;

import com.atguigu.crowd.entity.Auth;

import java.util.List;

/**
 * @author shuyun
 * @date 2024-09-05 09-10-33
 */
public interface AuthService {

    /**
     * 查询所有权限信息
     * @return
     */
    List<Auth> getAuthList();


    /**
     * 根据角色id查询已经分配的角色信息
     * @return
     */
    List<Auth> getAssignAuthList(Integer roleId);


    /**
     * 根据角色id删除已有权限
     * @param roleId
     */
    void deleteAuthByRoleId(Integer roleId);

    /**
     * 角色权限分配
     * @param authList
     * @param roleId
     */
    void saveAssignAuth(Integer roleId,List<Integer> authList);

    /**
     * 根据菜单id获取已有权限
     * @param id
     * @return
     */
    List<Auth> getMenuAssignAuthList(Integer id);

    /**
     * 根据菜单id删除已有权限
     * @param menuId
     */
    void deleteAuthByMenuId(Integer menuId);

    /**
     * 分配菜单权限
     * @param menuId
     * @param authList
     */
    void saveAssignMenuAuth(Integer menuId, List<Integer> authList);

    /**
     * 根据adminId查询已分配权限名称列表
     * @param adminId
     * @return
     */
    List<String> selectAuthNameByAdminId(Integer adminId);
}
