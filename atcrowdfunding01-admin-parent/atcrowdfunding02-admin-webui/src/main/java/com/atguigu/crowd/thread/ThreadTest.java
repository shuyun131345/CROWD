package com.atguigu.crowd.thread;

public class ThreadTest {
    public static void main(String[] args) {

        //模拟10个请求
        for (int i =1; i <= 10; i++){
            ThreadPoolUtil.execute(new FileRunnable());
        }


    }
}
