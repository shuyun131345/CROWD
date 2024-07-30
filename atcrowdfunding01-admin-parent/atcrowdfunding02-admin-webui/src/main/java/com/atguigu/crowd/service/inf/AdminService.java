package com.atguigu.crowd.service.inf;

import com.atguigu.crowd.entity.Admin;

import java.util.List;

public interface AdminService {
   Integer insertAdmin(Admin admin);

    List<Admin> getALl();
}
