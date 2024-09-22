package com.atguigu.crowd.mvc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.atguigu.crowd.constant.ExceptionConstant.ACCESS_DENY;

/**
 * @author shuyun
 * @date 2024-09-10 09:14:27
 */
@Configuration
@EnableWebSecurity
//启用全局方法权限控制功能，并且设置 prePostEnabled = true。保证@PreAuthority、@PostAuthority、@PreFilter、@PostFilter 生效
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebAppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService serDetailsSevice;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity security) throws Exception {
        //SpringSecurity 授权 相关的操作，主要是登录的账号、密码、权限、角色的认证。认证通过才授权
        security
                //对请求进行授权
                .authorizeRequests()
                //放行管理员登录页面
                .antMatchers("/admin/to/login/page.html").permitAll()
                //放行静态资源
                .antMatchers("/bootstrap/**", "/crowd/**", "/css/**", "/fonts/**", "/img/**", "/jquery/**", "/layer/**", "/script/**", "/ztree/**")
                .permitAll()
                //针对admin管理员分页展示界面进行权限控制的设置
                .antMatchers("/admin/page.html").hasRole("adminSelect")
                //访问admin修改页面，需要具备admin修改角色或者权限，二者有一个即可
                .antMatchers("/admin/editPage/**").access("hasRole('adminEdit') OR hasAuthority('user:edit')")
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
                .logoutSuccessUrl("/admin/to/login/page.html")
                //拒绝访问异常处理
                .and().exceptionHandling().accessDeniedHandler(new AccessDeniedHandler() {
                    @Override
                    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e)
                            throws IOException, ServletException {
                        //将异常信息存入到请求的session域
                        request.setAttribute("exception",new Exception(ACCESS_DENY));
                        //转发到自定义的错误页面，展示异常提示信息
                        request.getRequestDispatcher("/WEB-INF/error/system-error.jsp").forward(request,response);
                    }
                });

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //SpringSecurity 认证 相关的操作
        //临时测试，使用内存登录
        //auth.inMemoryAuthentication().withUser("tom").password("123456").roles("ADMIN");

        auth
                //基于数据库登录验证
                .userDetailsService(serDetailsSevice)
                //密码加密认证
                .passwordEncoder(passwordEncoder);

    }
}
