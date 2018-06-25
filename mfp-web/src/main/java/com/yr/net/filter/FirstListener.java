package com.yr.net.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by 215941826@qq.com on 2017/12/7.
 */

@WebListener
public class FirstListener implements ServletContextListener {
    static Logger log = LoggerFactory.getLogger(FirstListener.class);
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        log.info("FirstListener 初始化...");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        log.info("FirstListener 销毁...");
    }
}
