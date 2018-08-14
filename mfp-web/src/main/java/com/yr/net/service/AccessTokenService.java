package com.yr.net.service;

import com.alibaba.fastjson.JSON;
import com.yr.net.bean.GeneralToken;
import com.yr.net.http.HttpUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * All rights Reserved, Designed By SEGI
 * <pre>
 * Copyright:  Copyright(C) 2018
 * Company:    SEGI.
 * Author:     dengbp
 * CreateDate: 2018/6/20
 * </pre>
 * <p>
 *   微信token服务类
 * </p>
 */
@Service
public class AccessTokenService {
    protected final Logger logger = LoggerFactory.getLogger(AccessTokenService.class);
    @Value("${wx.global.token.url}")
    protected String url;

    /**
     * 调微信接口，取全局token
     * @return 全局token
     */
    public synchronized GeneralToken accessToken() {
        GeneralToken token = GeneralToken.instance;
        try {
            String res = HttpUtils.sendGet(this.url);
            Map map = JSON.parseObject(res,Map.class);
            if(map.get("access_token") != null) {
                String accessToken = String.valueOf(map.get("access_token"));
                long expireIn = Long.parseLong(String.valueOf(map.get("expires_in")));
                token.setAccessToken(accessToken);
                token.setExpiresIn(expireIn);
                logger.info("调用接口返回编码：{}",token.getErrorCode());
                logger.info("调用接口获取ACCESS_TOKEN成功：{}",token.getAccessToken());
                logger.info("调用接口获取EXPIRE_IN成功：{}",token.getExpiresIn());
            }

        } catch (Exception e) {
            logger.error("获取微信token出错：",e);
        }
        return token;
    }

    /**
     * 取可用的全局token
     * @return 全局token
     */
    public String getGlobalToken(){
        GeneralToken token = GeneralToken.instance;
        if(StringUtils.isBlank(token.getAccessToken())){
            accessToken();
        }
        return token.getAccessToken();
    }

    /**
     * 调微信接口取网页token
     * @param url 微信网页token地址
     * @return 网页token
     */
    public String getWebToken(String url){
        try {
            return HttpUtils.sendGet(url);
        } catch (Exception e) {
            logger.error("获取微信token出错：",e);
            return null;
        }
    }

    /**
     * 取微信用户资料
     * @param url url
     * @return 用户资料
     */
    public String getUserInfo(String url){
        try {
            return HttpUtils.sendGet(url);
        } catch (Exception e) {
            logger.error("获取微信用户信息出错：",e);
            return null;
        }
    }
}
