package com.atguigu.crowd.mvc.config;

import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.entity.Role;
import com.atguigu.crowd.entity.SecurityAdmin;
import com.atguigu.crowd.service.inf.AdminService;
import com.atguigu.crowd.service.inf.AssignService;
import com.atguigu.crowd.service.inf.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shuyun
 * @date 2024-09-21 15:25:39
 * @desc 自定义admin登录信息装配
 */
@Component
public class CrowdUserDetailsSevice implements UserDetailsService {

    @Autowired
    private AdminService adminService;

    @Autowired
    private AssignService assignService;

    @Autowired
    private AuthService authService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //1.根据用户名称查询admin信息
        Admin admin = adminService.selectAdminByAcount(username);
        Integer adminId = admin.getId();

        //2.根据adminId查询已分配的角色信息
        List<Role> roleList = assignService.selectAssignRolesByAdminId(adminId);

        //3.根据adminId查询权限信息
        List<String> authList = authService.selectAuthNameByAdminId(adminId);

        //4.组装SecurityAdmin实体类
        //存放角色和权限的集合
        List<GrantedAuthority> authorities = new ArrayList<>();

        //存入角色
        for(Role role : roleList){
            //security的底层是使用"ROLE_"作为角色的前缀，区分角色和权限，需要手动加上
            String roleName = "ROLE_" + role.getName();
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(roleName);
            authorities.add(simpleGrantedAuthority);
        }

        //存入权限
        for (String auth : authList){
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(auth);
            authorities.add(simpleGrantedAuthority);
        }

        //将带用户和密码的admin信息和角色、权限信息组装到SecurityAdmin中
        SecurityAdmin securityAdmin = new SecurityAdmin(admin, authorities);

        return securityAdmin;
    }
}
