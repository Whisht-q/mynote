package com.mynote.base.utils;

import com.mynote.base.exception.NoteException;
import com.mynote.base.constant.Enum.ResultCodeEnum;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @author zhishubin
 * @date 2023/12/18 16:06
 * @description
 */
@Component
public class RedisUtil {
    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    public RedisUtil(){}

    public boolean set(String key, Object value) {
        try {
            this.redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean set(String key, Object value, long time) {
        if (time > 0L) {
            this.redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
        } else {
            throw new NoteException(ResultCodeEnum.EXPIRE_TIME_ERROR);
        }
        return true;
    }

    public Object get(String key) {
        return key == null ? null : this.redisTemplate.opsForValue().get(key);
    }

    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                this.redisTemplate.delete(key[0]);
            } else {
                this.redisTemplate.delete(Arrays.asList(key));
            }
        }
    }

}
