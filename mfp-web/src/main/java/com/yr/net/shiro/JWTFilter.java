package com.yr.net.shiro;

import com.alibaba.fastjson.JSONObject;
import com.yr.net.bean.AjaxResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        if (StringUtils.isNotBlank(token)){
            return this.login(request,response,token);
        }
        /**
         * 获取请求的content-type
         */
        String contentType = request.getContentType();
        if (StringUtils.isBlank(contentType)){
            logger.warn("获取request的contentType为空！！！");
            response401(response);
            return false;
        }
        /**
         * 文件上传请求 *特殊请求，而且token还是空的
         */
        if(contentType.contains("multipart/form-data")){
            CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
            /**
             * CommonsMultipartResolver 是spring框架中自带的类，使用multipartResolver.resolveMultipart(final HttpServletRequest request)方法可以将request转化为MultipartHttpServletRequest
             * 使用MultipartHttpServletRequest对象可以使用getParameter(key)获取对应属性的值
             */
            MultipartHttpServletRequest multiReq = multipartResolver.resolveMultipart((HttpServletRequest)request);
            /**
             * 获取参数中的token
             */
            token = multiReq.getParameter("token");
            /**
             * 将转化后的request赋值到过滤链中的参数
             */
            request = multiReq;
            /**
             * 非文件上传请求
             */
            if (StringUtils.isNotBlank(token)){
                return this.login(request,response,token);
            }
        }
        /**
         * token如果还是空，尝试最后一次从body中获取
         */
        if (((HttpServletRequest)request).getMethod().equalsIgnoreCase("post") && contentType.contains("application/json")) {
            String responseStrBuilder = HttpHelper.getBodyString(request);
            Map<String ,String> map = JSONObject.parseObject(responseStrBuilder,Map.class);
            token = map.get("token");
            if (StringUtils.isNotBlank(token)){
                return this.login(request,response,token);
            }
        }
        logger.warn("请求异常,请求检查请求信息是否正常!!!");
        return false;
    }

    private boolean login(ServletRequest request,ServletResponse response,String token) throws Exception {
        logger.info("current token**************: " + token);
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
        HttpServletResponse response = (HttpServletResponse)resp;
        /**
         * 解决access control allow origin错误提示；使用通配符*，允许所有跨域访问，所以跨域访问成功。
         */
        response.setHeader("Access-Control-Allow-Origin", "*");
        //将实体对象转换为JSON Object转换
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        resp.getWriter().write(JSONObject.toJSONString(AjaxResponse.fail(401).setMsg("Unauthorized")));
        resp.getWriter().flush();
        resp.getWriter().close();
    }

    @Override
    public void doFilterInternal(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        /**
         * 防止流读取一次后就没有了, 所以需要将流继续写出去，提供后续使用
         */
        String contentType = request.getContentType();
        if(((HttpServletRequest)request).getMethod().equalsIgnoreCase("post") && null != contentType && contentType.contains("application/json")) {
            ServletRequest requestWrapper = new BodyReaderHttpServletRequestWrapper((HttpServletRequest)request);
            super.doFilterInternal(requestWrapper, response, chain);
        }else{
            super.doFilterInternal(request, response, chain);
        }

    }
}
