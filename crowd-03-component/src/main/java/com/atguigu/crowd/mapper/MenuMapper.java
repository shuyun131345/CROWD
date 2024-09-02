package com.atguigu.crowd.mapper;

import com.atguigu.crowd.entity.Menu;

import java.util.List;

/**
 * @author shuyun
 * @date 2024-09-01 16:37:09
 */
public interface MenuMapper {

    /**
     * 查询所有菜单节点信息
     * @return
     */
    List<Menu> queryMenuList();

    /**
     * 增加子节点
     * @param menu
     */
    void addChildrenMenu(Menu menu);
}
