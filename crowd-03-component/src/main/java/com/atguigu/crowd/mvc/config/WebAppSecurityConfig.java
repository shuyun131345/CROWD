package com.atguigu.crowd.mvc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author shuyun
 * @date 2024-09-10 09:14:27
 */
@Configuration
@EnableWebSecurity
public class WebAppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity security) throws Exception {
        //SpringSecurity 授权 相关的操作，主要是登录的账号、密码、权限、角色的认证。认证通过才授权

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //SpringSecurity 认证 相关的操作

    }
}
