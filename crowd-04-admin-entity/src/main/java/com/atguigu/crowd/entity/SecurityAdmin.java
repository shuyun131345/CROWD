package com.atguigu.crowd.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

/**
 * @author shuyun
 * @date 2024-09-21 14:02:58
 * @desc 考虑到 User 对象中仅仅包含账号和密码，为了能够获取到原始的Admin 对象，专门创建这个类对 User 类进行扩展
 */
public class SecurityAdmin extends User {

    private static final long serialVersionUID = 1L;
    //原始的角色信息
    private Admin admin;

    //传入原始的角色信息（只包含用户和密码）、权限和角色的集合
    public SecurityAdmin(Admin admin, List<GrantedAuthority> authorities){
        //调用父类的构造器
        super(admin.getLoginAcct(),admin.getUserPswd(),authorities);

        //给本类的原始管理员用户赋值
        this.admin = admin;

        //将本类中的原始的admin对象的密码擦除
        this.admin.setUserPswd(null);

    }

    //对外提供获取原始角色信息的get方法
    public Admin getAdmin(){
        return this.admin;
    }

}
