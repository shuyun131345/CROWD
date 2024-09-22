package com.atguigu.crowd.mvc.controller;

import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.entity.Student;
import com.atguigu.crowd.entity.common.AjaxResultEntity;
import com.atguigu.crowd.service.inf.AdminService;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
        return "admin/admin-login";
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
        // return "admin/admin-main";
        return "redirect:/admin/loginsuccess.html";
    }

    /**
     * 登录成功
     *
     * @return
     */
    @RequestMapping("/admin/loginsuccess.html")
    public String loginSuccess() {
        return "admin/admin-main";
    }


    /**
     * 管理员退出操作
     *
     * @param session
     * @return
     */
    @RequestMapping("/admin/logout.html")
    public String adminLogout(HttpSession session) {

        // 强制session失效
        session.invalidate();
        return "redirect:/admin/to/login/page.html";
    }


    /**
     * 按关键字查询管理员信息，分页展示
     *
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @param model
     * @return
     */
    @RequestMapping("/admin/page.html")
    public String adminPage(@Param("keyword") String keyword, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize, Model model) {

        PageInfo<Admin> pageInfo = service.selectAdminByKeyWord(keyword, pageNum, pageSize);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/admin-page";
    }


    /**
     * 根据id删除管理员信息，并重定向到信息查询页面
     *
     * @param id
     * @param pageNum
     * @param pageSize
     * @param keyword
     * @return
     */
    @RequestMapping("admin/remove/{id}/{pageNum}/{pageSize}/{keyword}.html")
    public String removeAdmin(@PathVariable("id") Integer id, @PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize, @PathVariable("keyword") String keyword) {

        // 根据id删除管理员信息
        int count = service.removeAdminByid(id);

        // 重定向是避免刷新页面时重新提交表单。返回到管理员信息查询页面，带上页码和关键字
        return "redirect:/admin/page.html?keyword=" + keyword + "&pageNum=" + pageNum + "&pageSize=" + pageSize;

    }


    /**
     * 增加管理员页面
     *
     * @return
     */
    @PreAuthorize("hasAuthority('user:add')")
    @RequestMapping("/admin/admin-add.html")
    public String addAdmin() {
        return "admin/admin-add";
    }


    /**
     * 检查通过后新增管理员信息
     *
     * @param admin
     * @return
     */
    @RequestMapping("/admin/addAdminCheck.html")
    public String addAdminCheck(Admin admin) {

        int count = service.addAdmin(admin);

        // 重定向是避免刷新页面时重新提交表单。返回到管理员信息查询最后一页
        return "redirect:/admin/page.html?pageNum=" + Integer.MAX_VALUE;
    }


    /**
     * 跳转到管理员信息修改页面，带分页信息和关键字
     *
     * @param id
     * @param pageNum
     * @param pageSize
     * @param keyword
     * @return
     */
    @RequestMapping("/admin/editPage/{id}/{pageNum}/{pageSize}/{keyword}.html")
    public String editAdminPage(@PathVariable("id") Integer id, @PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize, @PathVariable("keyword") String keyword, Model model) {

        Admin editAdmin = service.selectAdminById(id);
        model.addAttribute("editAdmin", editAdmin);
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("keyword", keyword);
        return "admin/admin-editPage";
    }


    /**
     * 修改管理员信息
     * @param admin
     * @return
     */
    @RequestMapping("/admin/edit/{id}/{pageNum}/{pageSize}/{keyword}.html")
    public String editAdmin(Admin admin,@PathVariable("id") Integer id,@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize, @PathVariable("keyword") String keyword) {
        admin.setId(id);
        int count = service.updateAdminById(admin);
        return "redirect:/admin/page.html?keyword=" + keyword + "&pageNum=" + pageNum + "&pageSize=" + pageSize;
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
