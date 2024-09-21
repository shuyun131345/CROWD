package com.atguigu.crowd.service.impl;

import com.atguigu.crowd.entity.Auth;
import com.atguigu.crowd.mapper.AuthMapper;
import com.atguigu.crowd.service.inf.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author shuyun
 * @date 2024-09-05 09-12-29
 */
@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthMapper mapper;

    @Override
    public List<Auth> getAuthList() {
        return mapper.selectAuthList();
    }

    @Override
    public List<Auth> getAssignAuthList(Integer roleId) {
        return mapper.selectAssignAuthList(roleId);
    }

    @Override
    public void deleteAuthByRoleId(Integer roleId) {
        mapper.deleteAuthByRoleId(roleId);
    }

    @Override
    public void saveAssignAuth(Integer roleId,List<Integer> authList) {
        mapper.saveAssignAuth(roleId,authList);
    }

    @Override
    public List<Auth> getMenuAssignAuthList(Integer id) {
        return mapper.getMenuAssignAuthList(id);
    }

    @Override
    public void deleteAuthByMenuId(Integer menuId) {
        mapper.deleteAuthByMenuId(menuId);
    }

    @Override
    public void saveAssignMenuAuth(Integer menuId, List<Integer> authList) {
        mapper.saveAssignMenuAuth(menuId,authList);
    }

    @Override
    public List<String> selectAuthNameByAdminId(Integer adminId) {
        return mapper.selectAuthNameByAdminId(adminId);
    }
}
