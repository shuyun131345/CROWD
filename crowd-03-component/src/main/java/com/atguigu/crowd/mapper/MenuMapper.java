package com.atguigu.crowd.mapper;

import com.atguigu.crowd.entity.Menu;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 根据id删除节点
     * @param id
     */
    void removeMenuById(@Param("id") Integer id);

    /**
     * 根据id更新节点信息
     * @param menu
     */
    void editMenuById(Menu menu);
}
