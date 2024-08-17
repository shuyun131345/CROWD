package com.atguigu.crowd.service.impl;

import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.entity.AdminExample;
import com.atguigu.crowd.mapper.AdminMapper;
import com.atguigu.crowd.service.inf.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author shuyun
 * @date 2024-08-17 15:49:25
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper mapper;
    @Override
    public List<Admin> selectAdminList() {
        List<Admin> adminList = mapper.selectByExample(new AdminExample());
        return adminList;
    }
}
