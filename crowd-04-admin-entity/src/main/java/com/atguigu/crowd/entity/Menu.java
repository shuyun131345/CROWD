package com.atguigu.crowd.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shuyun
 * @date 2024-09-01 16:27:01
 */
public class Menu {
    //当前节点编号
    private Integer id;
    //父节点
    private Integer pid;
    //节点名称
    private String name;

    //节点跳转地址
    private String url;
    //节点样式
    private String icon;

    //该节点的子节点，初始化是为了避免空指针
    private List<Menu> children = new ArrayList<>();

    //控制节点状态，默认为打开
    private Boolean open = true;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }
}
