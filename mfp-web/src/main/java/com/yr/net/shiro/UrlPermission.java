package com.yr.net.shiro;

import org.apache.shiro.authz.Permission;

import org.apache.shiro.util.AntPathMatcher;
import org.apache.shiro.util.PatternMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * All rights Reserved, Designed By SEGI
 * <pre>
 * Copyright:  Copyright(C) 2018
 * Company:    SEGI.
 * @Author:     dengbp
 * @Date: 2018/7/27
 * </pre>
 * <p>
 *    url权限拦截
 * </p>
 */
public class UrlPermission implements Permission {
	private static Logger logger = LoggerFactory.getLogger(UrlPermission.class);
	private String url;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public UrlPermission() {
	}
	
	public UrlPermission(String url) {
		this.url = url;
	}

	@Override
	public boolean implies(Permission p) {
		if(!(p instanceof UrlPermission)){
			return false;
		}
		UrlPermission up = (UrlPermission)p;
		// /admin/role/**
		PatternMatcher patternMatcher = new AntPathMatcher();
		logger.info(this.getUrl()+","+up.getUrl()+","+patternMatcher.matches(this.getUrl(), up.getUrl()));
		return patternMatcher.matches(this.getUrl(), up.getUrl());
	}

}