package com.atguigu.crowd.mvc.controller;

import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.entity.Student;
import com.atguigu.crowd.entity.common.AjaxResultEntity;
import com.atguigu.crowd.service.inf.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author shuyun
 * @date 2024-08-17 16:08:41
 */
@Controller
public class AdminController {

    private static final Logger log = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private AdminService service;

    @RequestMapping("/test/admin.html")
    public String selectAdminList(Model model){
        List<Admin> admins = service.selectAdminList();
        model.addAttribute("admins",admins);
        //异常测试
        // System.out.println(10/0);

        //空指针异常
        String s = null;
        System.out.println(s.toString());


        return "admin";
    }


    @RequestMapping("/test/ajax.html")
    @ResponseBody
    public String ajaxRequest(@RequestBody Student student){
        log.info(student.toString());
        //空指针异常
        String s = null;
        System.out.println(s.toString());
        return "success";
    }

    @RequestMapping("/test/ajaxresult.json")
    @ResponseBody
    public AjaxResultEntity<Student> ajaxResult(@RequestBody Student student){
        log.info(student.toString());
        //空指针异常
        String s = null;
        System.out.println(s.toString());
        AjaxResultEntity<Student> resultEntity = AjaxResultEntity.success(null, student);
        return resultEntity;
    }



}
