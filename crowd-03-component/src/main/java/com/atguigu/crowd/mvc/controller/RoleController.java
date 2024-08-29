package com.atguigu.crowd.mvc.controller;

import com.atguigu.crowd.entity.Role;
import com.atguigu.crowd.entity.common.AjaxResultEntity;
import com.atguigu.crowd.service.inf.RoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author shuyun
 * @date 2024-08-28 22:18:55
 */
@Controller
public class RoleController {

    @Resource
    private RoleService service;


    /**
     * 从控制面板跳转到角色维护界面
     * @return
     */
    @RequestMapping("/role/rolePage.html")
    public String rolePage(){
        return "role/role-page";
    }


    /**
     * 角色信息关键字查询并分页
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/role/pageInfo.json")
    @ResponseBody
    public AjaxResultEntity<PageInfo<Role>> getRolePageInfo(String keyword, Integer pageNum, Integer pageSize){
        PageInfo<Role> rolePageInfo = service.getRolePageInfo(keyword, pageNum, pageSize);
        return AjaxResultEntity.success(null,rolePageInfo);
    }


}
