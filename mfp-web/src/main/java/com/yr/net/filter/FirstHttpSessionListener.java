package com.yr.net.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.ArrayList;

/**
 * All rights Reserved, Designed By HQYG
 * <pre>
 * Copyright:  Copyright(C) 2018
 * Company:    HQYG.
 * Author:     dengbp
 * CreateDate: 2018/4/20
 * </pre>
 * <p>
 *     在线用户统计
 * </p>
 */
public class FirstHttpSessionListener implements HttpSessionListener {
    static Logger log= LoggerFactory.getLogger(FirstHttpSessionListener.class);

    //当前用户数
    private int userCounts=0;
    @Override
    public void sessionCreated(HttpSessionEvent se) {
//sessionCreated  用户数+1
        userCounts++;
        //重新在servletContext中保存userCounts
        se.getSession().getServletContext().setAttribute("userCounts", userCounts);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
//sessionDestroyed  用户数-1
        userCounts--;
        //重新在servletContext中保存userCounts
        se.getSession().getServletContext().setAttribute("userCounts", userCounts);

        @SuppressWarnings("unchecked")
        ArrayList<User> userList=(ArrayList<User>) se.getSession().getServletContext().getAttribute("userList");
        String sessionId=se.getSession().getId();
        //如果当前用户在userList中  在session销毁时  将当前用户移出userList
        if(new SessionUtil().getUserBySessionId(userList, sessionId)!=null){
            userList.remove(new SessionUtil().getUserBySessionId(userList, sessionId));
        }
        //将userList集合  重新保存到servletContext
        se.getSession().getServletContext().setAttribute("userList", userList);
    }


    class User {
        //当前用户的session id
        private String sessionId;
        //当前用户的ip地址
        private String ip;
        //当前用户第一次访问的时间
        private String firstTime;
        public User() {
            super();

        }
        public String getIp() {
            return ip;
        }
        public void setIp(String ip) {
            this.ip = ip;
        }
        public String getFirstTime() {
            return firstTime;
        }
        public void setFirstTime(String firstTime) {
            this.firstTime = firstTime;
        }
        public String getSessionId() {
            return sessionId;
        }
        public void setSessionId(String sessionId) {
            this.sessionId = sessionId;
        }

    }

    class SessionUtil {
        //根据sessionId判断当前用户是否存在在集合中  如果存在 返回当前用户  否则返回null
        public User getUserBySessionId(ArrayList<User> userList,String sessionId) {
            for (User user : userList) {
                if(sessionId.equals(user.getSessionId())){
                    return user;
                }
            }
            return null;
        }
    }
}
