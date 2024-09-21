package com.atguigu.crowd.mvc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author shuyun
 * @date 2024-09-10 09:14:27
 */
@Configuration
@EnableWebSecurity
public class WebAppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService serDetailsSevice;

    @Override
    protected void configure(HttpSecurity security) throws Exception {
        //SpringSecurity 授权 相关的操作，主要是登录的账号、密码、权限、角色的认证。认证通过才授权
        security
                //对请求进行授权
                .authorizeRequests()
                //放行管理员登录页面
                .antMatchers("/admin/to/login/page.html").permitAll()
                //放行静态资源
                .antMatchers("/bootstrap/**","/crowd/**","/css/**","/fonts/**","/img/**","/jquery/**","/layer/**","/script/**","/ztree/**")
                .permitAll()
                //其他请求，需要认证
                .anyRequest().authenticated()
                //禁用_csrf 防跨站请求伪造功能
                .and().csrf().disable()
                //开启表单登录功能
                .formLogin()
                //指定登录页面地址
                .loginPage("/admin/to/login/page.html")
                //loginProcessingUrl 指定提交登录表单的地址
                //loginProcessingUrl()方法指定了登录地址，就会覆盖 loginPage()方法中设置的默认值 /index.jsp POST
                .loginProcessingUrl("/security/do/login.html")
                //登录成功后前往的地址
                .defaultSuccessUrl("/admin/loginsuccess.html")
                //指定表单的用户、密码的参数名
                .passwordParameter("userPswd")
                .usernameParameter("loginAcct")
                .and()
                //开启退出登录功能
                .logout()
                //指定退出登录地址
                .logoutUrl("/security/logout.html")
                //指定退出登录成功后前往的地址：这里又回到登录页面
                .logoutSuccessUrl("/admin/to/login/page.html");

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //SpringSecurity 认证 相关的操作
        //临时测试，使用内存登录
        //auth.inMemoryAuthentication().withUser("tom").password("123456").roles("ADMIN");

        //基于数据库登录验证
        auth.userDetailsService(serDetailsSevice);

    }
}
