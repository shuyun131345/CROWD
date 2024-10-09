package com.atguigu.crowd.entity;

/**
 * @author shuyun
 * @date 2024-09-05 09-07-20
 */
public class Auth {

    private Integer id;

    private String name;

    private String title;

    private Integer catagoryId;

    public Auth() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCatagoryId() {
        return catagoryId;
    }

    public void setCatagoryId(Integer catagoryId) {
        this.catagoryId = catagoryId;
    }

    @Override
    public String toString() {
        return "Auth{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", catagoryId=" + catagoryId +
                '}';
    }
}
