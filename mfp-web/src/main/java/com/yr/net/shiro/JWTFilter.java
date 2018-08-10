package com.yr.net.shiro;

import com.alibaba.fastjson.JSONObject;
import com.yr.net.model.ResponseBean;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

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
        logger.info(String.valueOf(this.getSubject((HttpServletRequest)request, response).isAuthenticated()));
        String token = request.getParameter("token");
        /**
         * 获取请求的content-type
         */
        String contentType = request.getContentType();
        /**
         * 文件上传请求 *特殊请求，而且token还是空的
         */
        if(StringUtils.isBlank(token) && contentType.contains("multipart/form-data")){
            CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
            /**
             * CommonsMultipartResolver 是spring框架中自带的类，使用multipartResolver.resolveMultipart(final HttpServletRequest request)方法可以将request转化为MultipartHttpServletRequest
             * 使用MultipartHttpServletRequest对象可以使用getParameter(key)获取对应属性的值
             */
            MultipartHttpServletRequest multiReq = multipartResolver.resolveMultipart((HttpServletRequest)request);
            /**
             * 获取参数中的token
             */
            token=multiReq.getParameter("token");
            /**
             * 将转化后的request赋值到过滤链中的参数
             */
            request = multiReq;
            /**
             * 非文件上传请求
             */
        }
        /**
         * token如果还是空，尝试最后一次从body中获取
         */
        if (StringUtils.isBlank(token) && ((HttpServletRequest) request).getMethod().equalsIgnoreCase("post")) {
            String responseStrBuilder = HttpHelper.getBodyString(request);
            Map<String ,String> map = JSONObject.parseObject(responseStrBuilder,Map.class);
            token = map.get("token");
            logger.info("current token**************: " + token);
        }
        if (token != null) {
            try {
                JWTToken jwtToken = new JWTToken(token);
                // 提交给realm进行登入，如果错误他会抛出异常并被捕获
                getSubject(request, response).login(jwtToken);
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

    @Override
    public void doFilterInternal(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        // 防止流读取一次后就没有了, 所以需要将流继续写出去，提供后续使用
        String contentType = request.getContentType();//获取请求的content-type
        if(((HttpServletRequest)request).getMethod().equalsIgnoreCase("get") || contentType.contains("multipart/form-data")||contentType.contains("www-form-urlencoded")) {
            super.doFilterInternal(request, response, chain);
        }else{
            ServletRequest requestWrapper = new BodyReaderHttpServletRequestWrapper((HttpServletRequest)request);
            super.doFilterInternal(requestWrapper, response, chain);
        }

    }
}
