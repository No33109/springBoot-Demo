package com.example.demo.utils;


import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    /**
     *  指定缓存过期时间
     * @param key
     * @param time
     * @return
     */
    public Boolean expire(String key,Long time){
        try {
            if(time>0){
                redisTemplate.expire(key,time, TimeUnit.SECONDS);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取key过期时间
     * @param key
     * @return
     */
    public Long getExpire(String key){
        return redisTemplate.getExpire(key,TimeUnit.SECONDS);
    }


    /**
     * 判断key是否存在
     * @param key
     * @return
     */
    public Boolean hasKey(String key){
        try {
            return redisTemplate.hasKey(key);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除缓存
     * @param key 一个或者多个
     */
    @SuppressWarnings("unchecked")
    public void del(String... key){
        if(key != null && key.length >0){
            if(key.length == 1){
                redisTemplate.delete(key[0]);
            }else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    /**
     * get cache
     * @param key
     * @return
     */
    public Object get(String key){
        return  key == null ? null:redisTemplate.opsForValue().get(key);
    }

    /**
     * set cache
     * @param key
     * @param value
     * @return
     */
    public Boolean set(String key,Object value){
        try {
            redisTemplate.opsForValue().set(key,value);
            return  true;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * set cache expire time
     * @param key
     * @param value
     * @param time
     * @return
     */
    public Boolean set(String key,Object value,Long time){
        try {
            redisTemplate.opsForValue().set(key,value,time,TimeUnit.SECONDS);
            return true;
        }catch (Exception e){
            return  false;
        }
    }

}
