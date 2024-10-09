package com.atguigu.crowd.mvc.controller;

import com.atguigu.crowd.entity.Menu;
import com.atguigu.crowd.entity.common.AjaxResultEntity;
import com.atguigu.crowd.service.inf.MenuService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author shuyun
 * @date 2024-09-01 16:50:12
 */
@Controller
public class MenuController {
    @Autowired
    private MenuService service;

    /**
     * 跳转到菜单页面
     * @return
     */
    @RequestMapping("/menu/menuPage.html")
    public String MenuPage(){
        return "menu/menu-page";
    }


    /**
     * 获取菜单树形结构目录
     * @return
     */
    @RequestMapping("/menu/rootMenu.json")
    @ResponseBody
    public AjaxResultEntity<Menu> getRootMenu(){
        Menu root = service.getRootMenu();
        return AjaxResultEntity.success(null,root);
    }


    /**
     * 增加子节点
     * @param menu
     * @return
     */
    @RequestMapping("/menu/addMenu.json")
    @ResponseBody
    public AjaxResultEntity<String> addChildrenMenu(Menu menu){
        service.addChildrenMenu(menu);
        return AjaxResultEntity.success(null,null);
    }


    /**
     * 根据id删除子节点
     * @param id
     * @return
     */
    @RequestMapping("/menu/removeMenu.json")
    @ResponseBody
    public AjaxResultEntity<String> removeChildrenMenu( @Param("id") Integer id){
        service.removeMenuById(id);
        return AjaxResultEntity.success(null,null);
    }


    /**
     * 根据id更新节点信息
     * @param menu
     * @return
     */
    @RequestMapping("/menu/editMenu.json")
    @ResponseBody
    public AjaxResultEntity<String> editChildrenMenu( Menu menu){
        service.editMenuById(menu);
        return AjaxResultEntity.success(null,null);
    }


}
