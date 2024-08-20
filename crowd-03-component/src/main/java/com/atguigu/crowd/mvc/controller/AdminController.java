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

import javax.servlet.http.HttpSession;
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


    /**
     * 管理员登录界面
     *
     * @return
     */
    @RequestMapping("/admin/to/login/page.html")
    public String adminLogin() {
        return "login/admin-login";
    }


    /**
     * 登录检查，验证成功后跳转到登录成功界面
     *
     * @param admin
     * @param session
     * @return
     */
    @RequestMapping("/admin/do/login.html")
    public String adminLoginCheck(Admin admin, HttpSession session) {

        // 执行登录检查
        Admin adminLogin = service.checkAdminLogin(admin);

        // 将管理员信息存到session域，返回给前端
        session.setAttribute("admin", adminLogin);
        // return "login/admin-main";
        return "redirect:/admin/loginsuccess.html";
    }

    /**
     * 登录成功
     *
     * @return
     */
    @RequestMapping("/admin/loginsuccess.html")
    public String loginSuccess() {
        return "login/admin-main";
    }


    /**
     * 管理员退出操作
     * @param session
     * @return
     */
    @RequestMapping("/admin/logout.html")
    public String adminLogout(HttpSession session){

        //强制session失效
        session.invalidate();
        return "redirect:/admin/to/login/page.html";
    }



    //========test=====

    @RequestMapping("/test/admin.html")
    public String selectAdminList(Model model) {
        List<Admin> admins = service.selectAdminList();
        model.addAttribute("admins", admins);
        // 异常测试
        System.out.println(10 / 0);

        // 空指针异常
        // String s = null;
        // System.out.println(s.toString());


        return "admin";
    }


    @RequestMapping("/test/ajax.html")
    @ResponseBody
    public String ajaxRequest(@RequestBody Student student) {
        log.info(student.toString());
        // 空指针异常
        // String s = null;
        // System.out.println(s.toString());
        return "success";
    }

    @RequestMapping("/test/ajaxresult.json")
    @ResponseBody
    public AjaxResultEntity<Student> ajaxResult(@RequestBody Student student) {
        log.info(student.toString());
        // 空指针异常
        // String s = null;
        // System.out.println(s.toString());
        AjaxResultEntity<Student> resultEntity = AjaxResultEntity.success(null, student);
        return resultEntity;
    }


}
