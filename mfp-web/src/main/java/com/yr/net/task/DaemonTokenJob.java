package com.yr.net.task;

import com.yr.net.service.AccessTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * All rights Reserved, Designed By SEGI
 * <pre>
 * Copyright:  Copyright(C) 2018
 * Company:    SEGI.
 * @Author:     dengbp
 * @Date: 2018/6/20
 * </pre>
 * <p>
 *     定时任务
 * </p>
 */
@Component
@Configurable
@EnableScheduling
public class DaemonTokenJob {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    AccessTokenService accessTokenService;

    private final static long SECOND = 1000;


    /**
     * fixedDelay: 固定延迟时间执行
     */
    @Scheduled(fixedDelay = 1000 * SECOND)
    public void fixedDelayJob() {
        logger.info("test task,do nothing,{}\tfixedDelay", dateFormat().format(new Date()));
    }

    /**
     * fixedRate: 固定间隔时间执行
     */
    @Scheduled(fixedRate = 7100 * SECOND)
    public void fixedRate() {
        logger.info("开始执行获取微信token...");
        accessTokenService.accessToken();
    }

    /**
     * cron: 通过 Cron 表达式控制执行 1小时5分10秒执行一次
     */
    @Scheduled(cron = "*/10 5 1 * * *")
    public void cron() {
        logger.info("{}\tcron", dateFormat().format(new Date()));
    }

    private SimpleDateFormat dateFormat(){
        return new SimpleDateFormat ("HH:mm:ss");
    }
}
