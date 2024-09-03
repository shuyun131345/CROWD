package com.atguigu.crowd.service.impl;

import com.atguigu.crowd.entity.Menu;
import com.atguigu.crowd.mapper.MenuMapper;
import com.atguigu.crowd.service.inf.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author shuyun
 * @date 2024-09-01 16:48:31
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper mapper;

    @Override
    public Menu getRootMenu() {
        List<Menu> menuList = mapper.queryMenuList();
        //将菜单放到map中
        Map<Integer, Menu> menuMap = menuList.stream().collect(Collectors.toMap(Menu::getId, menu -> menu, (key1, key2) -> key2));
        //根节点
        Menu root = new Menu();
        //遍历 menuList，构造父子关系树.
        for (Menu menu : menuList) {
            //只有根节点的 pid 为空
            if (menu.getPid() == null) {
                root = menu;
                continue;
            }

            //不是根节点，找到该节点的父节点
            Menu parentMenu = menuMap.get(menu.getPid());
            //把该节点存到父节点的子列表中
            parentMenu.getChildren().add(menu);
        }

        //最终根节点就包含了所有的子节点，所以只返回根节点就行
        return root;
    }

    @Override
    public void addChildrenMenu(Menu menu) {
        //todo 插入重复异常处理
        mapper.addChildrenMenu(menu);
    }

    @Override
    public void removeMenuById(Integer id) {
        //todo 异常处理
        if (id == null || id == 0){
            return ;
        }
        mapper.removeMenuById(id);
    }

    @Override
    public void editMenuById(Menu menu) {
        //todo 异常处理 id不为空的判断
        mapper.editMenuById(menu);
    }
}
