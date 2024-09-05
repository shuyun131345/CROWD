package com.atguigu.crowd.mvc.controller;

import com.atguigu.crowd.entity.Auth;
import com.atguigu.crowd.entity.Role;
import com.atguigu.crowd.entity.common.AjaxResultEntity;
import com.atguigu.crowd.service.inf.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author shuyun
 * @date 2024-09-05 09-05-44
 */
@Controller
public class AuthController {

    @Autowired
    private AuthService service;

    /**
     * 查询所有权限信息
     * @return
     */
    @RequestMapping("/auth/getAuthList.json")
    @ResponseBody
    public AjaxResultEntity<List<Auth>> getAuthList(){
        List<Auth> authList = service.getAuthList();
        return AjaxResultEntity.success(null,authList);
    }

    /**
     * 根据角色id查询已有权限信息
     * @param role
     * @return
     */
    @RequestMapping("/auth/getAssignAuthList.json")
    @ResponseBody
    public AjaxResultEntity<List<Auth>> getAssignAuthList(Role role){
        List<Auth> authList = service.getAssignAuthList(role.getId());
        return AjaxResultEntity.success(null,authList);
    }

}
