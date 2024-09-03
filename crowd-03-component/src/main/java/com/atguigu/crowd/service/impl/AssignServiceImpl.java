package com.atguigu.crowd.service.impl;

import com.atguigu.crowd.entity.Role;
import com.atguigu.crowd.mapper.RoleMapper;
import com.atguigu.crowd.service.inf.AssignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author shuyun
 * @date 2024-09-03 21:13:45
 */
@Service
public class AssignServiceImpl implements AssignService {

    @Autowired
    private RoleMapper mapper;

    @Override
    public List<Role> selectAssignRolesByAdminId(Integer adminId) {
        return mapper.selectAssignRolesByAdminId(adminId);
    }

    @Override
    public List<Role> selectUnAssignRolesByAdminId(Integer adminId) {
        return mapper.selectUnAssignRolesByAdminId(adminId);
    }
}
