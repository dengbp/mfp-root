package com;


import com.yr.net.util.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 开启@Transactional 注解支持
 */
//@Configuration
//@EnableTransactionManagement(proxyTargetClass = true)
//@ComponentScan("com.yr.net")
@SpringBootApplication
public class Application {
    static Logger log = LoggerFactory.getLogger(Application.class);
    @Bean
    ReentrantLock lock(){
        return new ReentrantLock();
    }

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(Application.class, args);
        log.info("begin start server...");
        ReentrantLock lock = (ReentrantLock) SpringUtil.getBean("lock");
        log.info(lock.getClass().getName());
        log.info("start server success...");
//        new CountDownLatch(1).await();
    }

}