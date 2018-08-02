package com.yr.net.shiro;

import com.yr.net.bean.UsersBean;
import com.yr.net.service.UserService;
import com.yr.net.util.JWTUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * All rights Reserved, Designed By SEGI
 * <pre>
 * Copyright:  Copyright(C) 2018
 * Company:    SEGI.
 * @Author:     dengbp
 * @Date: 2018/7/27
 * </pre>
 * <p>
 *    定义Realm
 * </p>
 */
@Service
public class MyRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(MyRealm.class);

    @Resource
    private UserService userService;

    /**
     * 必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 权限验证
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String phone = JWTUtil.getUsername(principals.toString());
        Set<String> userRoles = new HashSet<>();
        Set<String> userPermissions = new HashSet<>();
        UsersBean user = userService.findByPhone(phone);
        if(null != user){
            userRoles.add(user.getRole());
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            simpleAuthorizationInfo.addRoles(userRoles);
            if(null != user.getPermission() && user.getPermission().split(",").length > 0){
                userPermissions = new HashSet<>(Arrays.asList(user.getPermission().split(",")));
            }
            logger.info("###【获取角色成功】[SessionId] => {}", SecurityUtils.getSubject().getSession().getId());
            simpleAuthorizationInfo.addStringPermissions(userPermissions);
            return simpleAuthorizationInfo;
        }else {
            throw new AuthorizationException();
        }
    }

    /**
     * 登陆验证
     * 首先执行这个登录验证
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getCredentials();
        // phone，用于和数据库进行对比
        String phone = JWTUtil.getUsername(token);
        if (phone == null) {
            throw new AuthenticationException("token invalid");
        }

        UsersBean userBean = userService.findByPhone(phone);
        if (userBean == null) {
            throw new AuthenticationException("User didn't existed!");
        }

        if (! JWTUtil.verify(token, phone, userBean.getPassword())) {
            throw new AuthenticationException("Username or password error");
        }

        return new SimpleAuthenticationInfo(token, token, "my_realm");
    }
}
