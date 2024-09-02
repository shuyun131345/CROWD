package com.atguigu.crowd.service.inf;

import com.atguigu.crowd.entity.Menu;

/**
 * @author shuyun
 * @date 2024-09-01 16:47:15
 */
public interface MenuService {

    /**
     * 获取根节点
     * @return
     */
    Menu getRootMenu();

    /**
     * 增加子节点
     * @param menu
     */
    void addChildrenMenu(Menu menu);

    /**
     * 根据id删除节点
     * @param id
     */
    void removeMenuById(Integer id);
}
