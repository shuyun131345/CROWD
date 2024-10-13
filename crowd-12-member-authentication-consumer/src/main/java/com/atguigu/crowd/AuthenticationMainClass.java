package com.atguigu.crowd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

//开启feign客户端功能
@EnableFeignClients
@EnableDiscoveryClient // 当前版本可以不写
@SpringBootApplication
public class AuthenticationMainClass {
    public static void main(String[] args) {
        SpringApplication.run(AuthenticationMainClass.class);
        System.out.println("Hello world!");
    }
}