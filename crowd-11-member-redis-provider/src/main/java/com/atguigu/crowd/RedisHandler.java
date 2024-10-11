package com.atguigu.crowd;

import com.atguigu.crowd.entity.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author shuyun
 * @date 2024-10-11 09:08:21
 */
@RestController
public class RedisHandler {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * redis操作，不带超时的set方法
     *
     * @param key
     * @param value
     * @return
     */
    @RequestMapping("/set/redis/key/value/remote")
    ResultEntity<String> setRedisKeyValueRemote(@RequestParam("key") String key, @RequestParam("value") String value){
        try{
            ValueOperations<String, String> operations = redisTemplate.opsForValue();
            operations.set(key,value);
        }catch (Exception e){
            return ResultEntity.failed(e.getMessage());
        }
        return ResultEntity.successWithoutData();
    }


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
            @RequestParam("timeUnit") TimeUnit timeUnit){

        try{
            ValueOperations<String, String> operations = redisTemplate.opsForValue();
            operations.set(key,value,time,timeUnit);
        }catch (Exception e){
            return ResultEntity.failed(e.getMessage());
        }
        return ResultEntity.successWithoutData();
    }

    /**
     * reddis操作，根据key获取value
     * @param key
     * @return
     */
    @RequestMapping("/get/redis/value/by/key/remote")
    ResultEntity<String> getRedisValueByKeyRemote(@RequestParam("key") String key){
        try{
            ValueOperations<String, String> operations = redisTemplate.opsForValue();
            String value = operations.get(key);
            return ResultEntity.successWithData(value);
        }catch (Exception e){
            return ResultEntity.failed(e.getMessage());
        }
    }

    /**
     * redis操作，删除key
     * @param key
     * @return
     */
    @RequestMapping("/remove/redis/key/remote")
    ResultEntity<String> removeRedisKeyRemote(@RequestParam("key") String key){
        try{
            redisTemplate.delete(key);
            return ResultEntity.successWithoutData();
        }catch (Exception e){
            return ResultEntity.failed(e.getMessage());
        }
    }
}
