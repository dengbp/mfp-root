package com.yr.net.service;

import com.yr.net.config.RedisConfig;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * All rights Reserved, Designed By SEGI
 * <pre>
 * Copyright:  Copyright(C) 2018
 * Company:    SEGI.
 * @Author: dengbp
 * @Date: 2018/8/8
 * </pre>
 * <p>
 *     redis服务类
 * </p>
 */
@Service
public class RedisService {
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(RedisService.class);
    @Resource
    JedisPool jedisPool;

    @Autowired
    RedisConfig redisConfig;

    public Jedis getInstance(){
        String uuid = UUID.randomUUID().toString();
        logger.info("jedisPool uuid : " + uuid);
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis;
        }
    }
}
