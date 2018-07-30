package com.yr.net.shiro;

import com.alibaba.fastjson.JSONObject;
import com.yr.net.bean.AjaxResponse;
import com.yr.net.model.ResponseBean;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * All rights Reserved, Designed By SEGI
 * <pre>
 * Copyright:  Copyright(C) 2018
 * Company:    SEGI.
 * @Author: dengbp
 * @Date: 2018/7/27
 * </pre>
 * <p>
 * swt过滤器
 * </p>
 */
public class JWTFilter extends BasicHttpAuthenticationFilter {
   private static Logger logger = LoggerFactory.getLogger(JWTFilter.class);

    /**
     * 表示是否允许访问
     * @param request request
     * @param response response
     * @param mappedValue 就是[urls]配置中拦截器参数部分
     * @return 如果允许访问返回true，否则false
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        logger.info(String.valueOf(this.getSubject(httpServletRequest, response).isAuthenticated()));
        return super.isAccessAllowed(request, response, mappedValue);
    }

    /**
     * 表示当访问拒绝时是否已经处理了
     * @param request request
     * @param response response
     * @return 如果返回true表示需要继续处理；如果返回false表示该拦截器实例已经处理了，将直接返回即可
     * @throws Exception Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        // 获取Authorization字段
        logger.info("isAuthenticated");
        logger.info(String.valueOf(this.getSubject(httpServletRequest, response).isAuthenticated()));

        String authorization = httpServletRequest.getHeader("Authorization");
        if (authorization != null) {
            try {
                JWTToken token = new JWTToken(authorization);
                // 提交给realm进行登入，如果错误他会抛出异常并被捕获
                getSubject(request, response).login(token);
                return true;
            } catch (Exception e) {
                response401(response);
                return false;
            }
        } else {
            response401(response);
            return false;
        }
    }

    @Override
    public void setLoginUrl(String loginUrl) {
        super.setLoginUrl(loginUrl);
    }

    /**
     * 直接返回错误信息
     *
     * @param resp resp
     * @throws Exception Exception
     */
    private void response401(ServletResponse resp) throws Exception {
        ResponseBean responseBean = new ResponseBean(401, "Unauthorized", null);
        resp.getWriter().write(JSONObject.toJSONString(responseBean));
        resp.getWriter().flush();
        resp.getWriter().close();
    }
}
