package com.atguigu.crowd.mvc.controller;

import com.atguigu.crowd.entity.Role;
import com.atguigu.crowd.service.inf.AssignService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
     * 查询Admin用户拥有角色和未拥有角色
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/assignrole/assign-role.html")
    public String assignRolePage(@RequestParam("adminId") Integer id, Model model){

        //获取已拥有权限
        List<Role> assignRoles = service.selectAssignRolesByAdminId(id);

        //获取未拥有权限
        List<Role> unAssignRoles = service.selectUnAssignRolesByAdminId(id);

        //存入模型返回前端页面.存入模型（本质上其实是：request.setAttribute("attrName",attrValue);
        model.addAttribute("assignRoles",assignRoles);
        model.addAttribute("unAssignRoles",unAssignRoles);

        return "assignrole/assign-role";
    }


    /**
     * 给用户分配角色
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @param adminId
     * @param roleIdList
     * @return
     */
    @RequestMapping("/assignrole/saveAssignRoles.json")
    public String saveAssignRoles(@RequestParam("keyword") String keyword,
                                                    @RequestParam("pageNum") Integer pageNum,
                                                    @RequestParam("pageSize") Integer pageSize,
                                                    @RequestParam("adminId") Integer adminId,
                                                    //因为用户可以没有角色，所有角色列表可以为空
                                                    @RequestParam(value = "roleIdList",required = false) List<Integer> roleIdList){
        //先删除原有的角色
        service.deleteOriAssignRoles(adminId);

        //再添加新的角色，非空才插入，角色列表为空时只做删除即可
        if (!CollectionUtils.isEmpty(roleIdList)){
            service.saveNewAssignRoles(adminId,roleIdList);
        }
        return "redirect:/admin/page.html?keyword=" + keyword + "&pageNum=" + pageNum + "&pageSize=" + pageSize;
    }


}
