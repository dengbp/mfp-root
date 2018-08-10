package com.yr.net.shiro;

import com.yr.net.util.SpringUtil;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * All rights Reserved, Designed By SEGI
 * <pre>
 * Copyright:  Copyright(C) 2018
 * Company:    SEGI.
 * @Author:     dengbp
 * @Date: 2018/7/27
 * </pre>
 * <p>
 *    Shiro配置
 *    url配置方式，自定义拦截器，使用jwt认证方式;
 *    ShiroFilterChainDefinition注释打开后，开启session功能，即可支持权限注解配置方式，
 *    但是需要使用session功能，前端则需要cookie支持
 * </p>
 */
@Configuration
public class ShiroConfig {

    @Bean
    public MyRealm myRealm(){
        MyRealm myRealm = new MyRealm();
        return myRealm;
    }

    @Bean
    public JWTFilter JWTFilter(){
        JWTFilter jwtFilter = new JWTFilter();
        return jwtFilter;
    }

    @Bean("securityManager")
    public DefaultWebSecurityManager getManager() {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        MyRealm myRealm = myRealm();
        myRealm.setPermissionResolver(new UrlPermissionResovler());
        manager.setRealm(myRealm);
        /*
         * 关闭shiro自带的session，详情见文档
         * http://shiro.apache.org/session-management.html#SessionManagement-StatelessApplications%28Sessionless%29
         */
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        manager.setSubjectDAO(subjectDAO);
        return manager;
    }

    @Bean("shiroFilterFactoryBean")
    public ShiroFilterFactoryBean factory(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();

        // 添加自己的过滤器并且取名为jwt
        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("jwt", JWTFilter());
        factoryBean.setFilters(filterMap);
        factoryBean.setSecurityManager(securityManager);
        factoryBean.setUnauthorizedUrl("/401");
        //拦截器.
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();

        filterChainDefinitionMap.put("/edit", "jwt, perms[edit]");
//        filterChainDefinitionMap.put("/file/single/upload", "jwt");
//        filterChainDefinitionMap.put("/users/test/update", "jwt");
        filterChainDefinitionMap.put("/users/test/edit", "jwt");
        filterChainDefinitionMap.put("/admin/hello", "jwt, roles[admin]");
        filterChainDefinitionMap.put("/annotation/**", "jwt");
        filterChainDefinitionMap.put("/**", "anon");
        factoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return factoryBean;
    }

    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chain = new DefaultShiroFilterChainDefinition();
        // 由于demo1展示统一使用注解做访问控制，所以这里配置所有请求路径都可以匿名访问
        chain.addPathDefinition("/**", "anon"); // all paths are managed via annotations
        // 这另一种配置方式。但是还是用上面那种吧，容易理解一点。
        // or allow basic authentication, but NOT require it.
        // chainDefinition.addPathDefinition("/**", "authcBasic[permissive]");
        return chain;
    }

    @Bean
    protected CacheManager cacheManager() {
        return new MemoryConstrainedCacheManager();
    }

    /**
    * 添加注解支持(注解权限配置，需要cookie支持，后端需要session功能开启)*/
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        /**
         * 使用权限注解生效
         * 强制使用cglib，防止重复代理和可能引起代理出错的问题
         */
        creator.setProxyTargetClass(true);
        return new DefaultAdvisorAutoProxyCreator();
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * AOP配置类(专门处理Shiro权限注解的方法拦截)
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
}
