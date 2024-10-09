package com.atguigu.crowd.mvc.controller;

import com.atguigu.crowd.entity.Auth;
import com.atguigu.crowd.entity.common.AjaxResultEntity;
import com.atguigu.crowd.service.inf.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

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
     * @param id
     * @return
     */
    @RequestMapping("/auth/getAssignAuthList.json")
    @ResponseBody
    public AjaxResultEntity<List<Auth>> getAssignAuthList(Integer id){
        List<Auth> authList = service.getAssignAuthList(id);
        return AjaxResultEntity.success(null,authList);
    }


    /**
     * 角色权限分配
     * @param reqMap
     * @return
     */
    @RequestMapping("/auth/saveAssignAuthList.json")
    @ResponseBody
    public AjaxResultEntity<String> saveAssignAuth(@RequestBody Map<String,List<Integer>> reqMap){

        //角色id
        Integer roleId = reqMap.get("roleId").get(0);
        //权限列表
        List<Integer> authList = reqMap.get("authList");
        //先删除已有权限
        //todo 异常处理

        if (roleId == null || roleId == 0){

        }

        service.deleteAuthByRoleId(roleId);

        //再增加新分配的权限
        if (!CollectionUtils.isEmpty(authList)){
            service.saveAssignAuth(roleId,authList);
        }
        return AjaxResultEntity.success(null,null);
    }


    /**
     * 根据菜单id查询已分配权限
     * @param id
     * @return
     */
    @RequestMapping("/auth/getMenuAssignAuthList.json")
    @ResponseBody
    public AjaxResultEntity<List<Auth>> getMenuAssignAuthList(Integer id){
        List<Auth> authList = service.getMenuAssignAuthList(id);
        return AjaxResultEntity.success(null,authList);
    }

    /**
     * 菜单权限分配
     * @param reqMap
     * @return
     */
    @RequestMapping("/auth/saveAssignMenuAuthList.json")
    @ResponseBody
    public AjaxResultEntity<String> saveAssignMenuAuthList(@RequestBody Map<String,List<Integer>> reqMap){

        //角色id
        Integer menuId = reqMap.get("menuId").get(0);
        //权限列表
        List<Integer> authList = reqMap.get("authList");
        //先删除已有权限
        //todo 异常处理

        if (menuId == null || menuId == 0){

        }

        service.deleteAuthByMenuId(menuId);

        //再增加新分配的权限
        if (!CollectionUtils.isEmpty(authList)){
            service.saveAssignMenuAuth(menuId,authList);
        }
        return AjaxResultEntity.success(null,null);
    }


}
