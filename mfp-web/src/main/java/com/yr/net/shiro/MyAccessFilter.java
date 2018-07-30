package com.yr.net.shiro;

import org.apache.shiro.web.filter.AccessControlFilter;
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
 * @Author:     dengbp
 * @Date: 2018/7/27
 * </pre>
 * <p>
 *    定义根据url判断是否有权限访问，在JWTFiler后
 * </p>
 */
@Deprecated
public class MyAccessFilter extends AccessControlFilter {
    private Logger logger = LoggerFactory.getLogger(MyAccessFilter.class);

    /**
     * 表示是否允许访问
     * @param request request
     * @param response response
     * @param mappedValue 就是[urls]配置中拦截器参数部分
     * @return 如果允许访问返回true，否则false
     */
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
        logger.info("accessFilter");
        logger.info("isAuthenticated1");
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        logger.info(this.getPathWithinApplication(request));
        String url = this.getPathWithinApplication(request);
        logger.info(String.valueOf(this.getSubject(httpServletRequest, response).isAuthenticated()));
        return getSubject(request, response).isPermitted(url);
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
		response401(request,response);
		return false;
	}

    /**
     * 将请求返回到 /401
     * @param req req
     * @param resp resp
     * @throws Exception Exception
     */
    private void response401(ServletRequest req, ServletResponse resp) throws Exception {
        HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
        httpServletResponse.sendRedirect("/401");
    }
}
