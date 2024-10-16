package com.atguigu.crowd.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MemberRegisterConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 浏览器访问的地址
        String path = "/auth/member/register";
        // 目标视图的名称，将来拼接“prefix: classpath:/templates/”、“suffix: .html”前后缀
        String viewName = "register/member-register";
        //从首页跳转到注册页面
        registry.addViewController(path).setViewName(viewName);
        //从首页跳转到登录界面
        registry.addViewController("/auth/member/login").setViewName("login/member-login");
        //从登录页面跳转到个人中心
        registry.addViewController("/auth/to/member-center").setViewName("member/member-center");
        registry.addViewController("/auth/project/to/my-crowd").setViewName("project/my-crowd");
    }
}
