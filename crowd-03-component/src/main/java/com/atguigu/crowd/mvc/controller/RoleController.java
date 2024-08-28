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

    @RequestMapping("/role/pageInfo.json")
    @ResponseBody
    public AjaxResultEntity<PageInfo<Role>> getRolePageInfo(String keyWord, Integer pageNum, Integer pageSize){
        PageInfo<Role> rolePageInfo = service.getRolePageInfo(keyWord, pageNum, pageSize);
        return AjaxResultEntity.success(null,rolePageInfo);
    }


}
