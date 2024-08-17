package com.atguigu.crowd.controller;

import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.service.inf.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author shuyun
 * @date 2024-08-17 16:08:41
 */
@Controller
public class AdminController {

    @Autowired
    private AdminService service;

    @RequestMapping("/test/admin.html")
    public String selectAdminList(Model model){
        List<Admin> admins = service.selectAdminList();
        model.addAttribute("admins",admins);
        return "admin";
    }

}
