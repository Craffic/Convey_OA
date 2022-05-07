package com.craffic.convey.jobserver.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisUtil extends ApplicationObjectSupport {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    /**
     * 插入数据到redis
     * @param key
     * @param value
     * @param minutes
     * @return
     */
    public boolean insertData(String key, String value, long minutes){
        try {
            if (minutes <= 0) {
                redisTemplate.opsForValue().set(key, value);
            } else {
                redisTemplate.opsForValue().set(key, value, minutes, TimeUnit.MINUTES);
            }
            return true;
        } catch (Exception e) {
            logger.error("插入redis数据出错");
            return false;
        }
    }

    /**
     * 通过key查询value
     */
    public String queryByKey(String key){
        try{
            String value = redisTemplate.opsForValue().get(key);
            return value;
        } catch (Exception e) {
            logger.error("查询redis出错，key={}", key);
            return null;
        }
    }

    /**
     * 查询是否被锁
     */
    public Boolean queryIsLocked(String key){
        String value = queryByKey(key);
        return StringUtils.isNotEmpty(value);
    }



}
