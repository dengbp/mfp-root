package com.yr.net.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.util.Collections;

/**
 * All rights Reserved, Designed By SEGI
 * <pre>
 * Copyright:  Copyright(C) 2018
 * Company:    SEGI.
 * @Author: dengbp
 * @Date: 2018/8/7
 * </pre>
 * <p>
 *     分布式锁
 * </p>
 */
public class DistributedLock {
   private static Logger logger = LoggerFactory.getLogger(DistributedLock.class);
    private String nodeId;
    private static Jedis jedis = null;
    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final DistributedLock instance = new DistributedLock();
    /**
     * 加过期设置
     */
    private static final String SET_WITH_EXPIRE_TIME = "PX";
    private static final Long UNLOCK_SUCCESS = 1L;
    private static final String SCRIPT = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";

    private DistributedLock() {

    }

    public DistributedLock init(String nodeId,String redisHost,Integer redisPort){
        this.nodeId = nodeId;
        jedis = new Jedis(redisHost, redisPort);
        return instance;
    }

    /**
     * 尝试获取分布式锁
     * @param lockKey 锁
     * @param expireTime 超期时间
     */
    public  void tryLock(String lockKey, int expireTime) {
        while (!LOCK_SUCCESS.equals(jedis.set(lockKey, nodeId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime))) {
            long sleep = expireTime/10;
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        logger.info("获取分布式锁成功...");
    }

    /**
     * 释放分布式锁
     * @param lockKey 锁
     * @return 是否释放成功
     */
    public  boolean unLock( String lockKey) {
        Object result = jedis.eval(SCRIPT, Collections.singletonList(lockKey), Collections.singletonList(nodeId));
        if (UNLOCK_SUCCESS.equals(result)) {
            logger.info("解分布式锁成功...");
            return true;
        }
        logger.info("解分布式锁失败...");
        return false;
    }
}
