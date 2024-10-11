package com.atguigu.crowd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RedisMainClass {
    public static void main(String[] args) {
        SpringApplication.run(RedisMainClass.class);
        System.out.println("Hello world!");
    }
}