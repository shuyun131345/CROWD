package com.atguigu.crowd.service.inf;

import com.atguigu.crowd.entity.Admin;

import java.util.List;

/**
 * @author shuyun
 * @date 2024-08-17 15:48:30
 */
public interface AdminService {

    /**
     * 查询Admin列表
     * @return List<Admin>
     */
    List<Admin> selectAdminList();
}
