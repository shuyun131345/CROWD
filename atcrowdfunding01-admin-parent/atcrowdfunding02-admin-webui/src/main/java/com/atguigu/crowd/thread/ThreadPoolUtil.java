package com.atguigu.crowd.thread;

import java.util.concurrent.*;

public class ThreadPoolUtil {

    private static volatile ThreadPoolExecutor pool = null;

    //无响应时执行
    public static void execute(Runnable runnable){
        getThreadPool().execute(runnable);
    }

    //有响应时执行
    public static<T> Future<T> submit(Callable<T> callable){
        return getThreadPool().submit(callable);
    }


    private static  ThreadPoolExecutor getThreadPool(){
        //获取当前机器处理器数量
        int cupNUm = Runtime.getRuntime().availableProcessors();
        //根据cpu数量计算出合理的线程并发数
        int maximumPoolSize = cupNUm*2 + 1;
        //空闲线程最大存活时间 5秒
        int keepAliveTime = 5;
        //拒绝策略：任务数>最大线程+阻塞队列时，拒绝任务
        if ( pool == null){
            synchronized(ThreadPoolUtil.class){
                if (pool == null){
                    pool = new ThreadPoolExecutor(maximumPoolSize,maximumPoolSize+1,keepAliveTime, TimeUnit.SECONDS,
                            new ArrayBlockingQueue<Runnable>(50),Executors.defaultThreadFactory());
                }
            }
        }
        return pool;
    }
}
