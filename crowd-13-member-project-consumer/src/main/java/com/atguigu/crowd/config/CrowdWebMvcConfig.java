package com.atguigu.crowd.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CrowdWebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //view-controller 是在 project-consumer 内部定义的，所以这里是一个不经过Zuul访问的地址，所以这个路径前面不加路由规则中定义的前缀：“/project”
        registry.addViewController("/to/agree/protocol/page").setViewName("protocal-agree");

    }
}
