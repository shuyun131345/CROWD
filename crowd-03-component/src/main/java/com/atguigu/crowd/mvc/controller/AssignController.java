package com.atguigu.crowd.mvc.controller;

import com.atguigu.crowd.entity.Role;
import com.atguigu.crowd.service.inf.AssignService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author shuyun
 * @date 2024-09-03 20:50:59
 */
@Controller
public class AssignController {

    @Resource
    private AssignService service;


    /**
     * 查询用户拥有角色和未拥有角色
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/assignrole/assign-role/{id}/{pageNum}/{pageSize}/{keyword}.html")
    public String assignRolePage(@PathVariable("id") Integer id, Model model){

        //获取已拥有权限
        List<Role> assignRoles = service.selectAssignRolesByAdminId(id);

        //获取未拥有权限
        List<Role> unAssignRoles = service.selectUnAssignRolesByAdminId(id);

        //存入模型返回前端页面.存入模型（本质上其实是：request.setAttribute("attrName",attrValue);
        model.addAttribute("assignRoles",assignRoles);
        model.addAttribute("unAssignRoles",unAssignRoles);

        return "assignrole/assign-role";
    }



}
