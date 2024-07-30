package com.atguigu.crowd.mvc.handler;

import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.service.inf.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class TestHandler {
    @Autowired
    private AdminService adminService;


    @RequestMapping("/test/ssm.html")
    public String testSsm(Model model){
        List<Admin> list = adminService.getALl();
        model.addAttribute("list",list);
        return "target";
    }
}
