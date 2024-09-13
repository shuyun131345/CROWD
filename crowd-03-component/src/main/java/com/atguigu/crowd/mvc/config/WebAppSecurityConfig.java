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
        security
                //对请求进行授权
                .authorizeRequests()
                //对登录页进行设置
                .antMatchers("/admin/to/login/page.html")
                //无条件访问
                .permitAll()
               //放行静态资源
                .antMatchers("/bootstrap/**","/crowd/**","/css/**","/fonts/**","/img/**","/jquery/**","/layer/**","/script/**","/ztree/**")
                .permitAll()
                //其他请求，均需要授权
                .anyRequest()
                .authenticated()
                .and()
                //禁用csrf跨站请求伪功能，否则所有请求都需要带上_csrf
                .csrf()
                .disable()
                //开启表单登录的功能
                .formLogin()
                //指定登录页面
                .loginPage("/admin/to/login/page.html")
                //loginProcessingUrl 指定提交登录表单的URL地址
                //loginProcessingUrl()方法指定了登录地址，就会覆盖 loginPage()方法中设置的默认值 /index.jsp POST
                .loginProcessingUrl("/security/do/login.html")
                //指定登录成功后前往的地址
                .defaultSuccessUrl("/admin/loginsuccess.html")
                //定制表单用户名请求参数
                .usernameParameter("loginAcct")
                //定制表单密码请求参数
                .passwordParameter("userPswd");

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //SpringSecurity 认证 相关的操作
        auth.inMemoryAuthentication().withUser("tom").password("123456").roles("ADMIN");


    }
}
