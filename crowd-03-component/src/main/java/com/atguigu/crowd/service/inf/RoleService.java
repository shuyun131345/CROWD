package com.atguigu.crowd.service.inf;

import com.atguigu.crowd.entity.Role;
import com.github.pagehelper.PageInfo;

/**
 * @author shuyun
 * @date 2024-08-28 22:46:39
 */
public interface RoleService {

    /**
     * 按关键字查询角色信息并分页
     * @param keyWord
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<Role> getRolePageInfo(String keyWord, Integer pageNum, Integer pageSize);

    /**
     * 新增角色信息
     * @param roleName
     * @return
     */
    int saveRole(String roleName);

    /**
     * 根据id更新角色
     * @param role
     * @return
     */
    int updateRoleByid(Role role);
}
