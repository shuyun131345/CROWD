package com.atguigu.crowd.service.inf;

import com.atguigu.crowd.entity.Role;

import java.util.List;

/**
 * @author shuyun
 * @date 2024-09-03 21:11:07
 */
public interface AssignService {

    /**
     * 根据adminId查询该用户已有权限
     * @param adminId
     * @return
     */
    List<Role> selectAssignRolesByAdminId(Integer adminId);

    /**
     * 根据adminId查询该用户未有权限
     * @param adminId
     * @return
     */
    List<Role> selectUnAssignRolesByAdminId(Integer adminId);

    /**
     * 删除用户原有角色
     * @param adminId
     */
    void deleteOriAssignRoles(Integer adminId);

    /**
     * 用户增加角色
     * @param adminId
     * @param roleIdList 角色列表
     */
    void saveNewAssignRoles(Integer adminId, List<Integer> roleIdList);
}
