package com.yr.net.filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by 215941826@qq.com on 2017/12/7.
 */

@WebFilter(filterName="firstFilter", urlPatterns = {
        "/welcome2/*",
        "/login2/*"
})
public class FirsrtFilter implements Filter {
    static Log log = LogFactory.getLog(FirsrtFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String requestURI = req.getRequestURI();
        log.info("过滤到的请求--->"+requestURI);
    }

    @Override
    public void destroy() {

    }
}
