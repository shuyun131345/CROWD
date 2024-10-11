package com.atguigu.crowd.api;

import com.atguigu.crowd.entity.ResultEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.TimeUnit;

/**
 * @author shuyun
 * @date 2024-10-11 08:52:27
 * @FeignClient注解表示和一个provider对应，value属性指定要调用的Provider的微服务名称;
 * fallbackFactory属性指定 consumer 调用 provider 时如果失败所采取的备用方案
  */
@FeignClient(value = "redis-provider")
public interface RedisRemoteService {

    /**
     * redis操作，不带超时的set方法
     *
     * @param key
     * @param value
     * @return
     */
    @RequestMapping("/set/redis/key/value/remote")
    ResultEntity<String> setRedisKeyValueRemote(@RequestParam("key") String key, @RequestParam("value") String value);


    /**
     * redis操作，带超时的set方法
     * @param key
     * @param value
     * @param time
     * @param timeUnit
     * @return
     */
    @RequestMapping("/set/redis/key/value/remote/with/timeout")
    ResultEntity<String> setRedisKeyValueRemoteWithTimeout(
            @RequestParam("key") String key,
            @RequestParam("value") String value,
            @RequestParam("time") long time,
            @RequestParam("timeUnit") TimeUnit timeUnit);

    /**
     * reddis操作，根据key获取value
     * @param key
     * @return
     */
    @RequestMapping("/get/redis/value/by/key/remote")
    ResultEntity<String> getRedisValueByKeyRemote(@RequestParam("key") String key);

    /**
     * redis操作，删除key
     * @param key
     * @return
     */
    @RequestMapping("/remove/redis/key/remote")
    ResultEntity<String> removeRedisKeyRemote(@RequestParam("key") String key);

}
