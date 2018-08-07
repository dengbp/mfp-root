package com.yr.net.model;

import java.io.Serializable;

/**
 * All rights Reserved, Designed By SEGI
 * <pre>
 * Copyright:  Copyright(C) 2018
 * Company:    SEGI.
 * @Author: dengbp
 * @Date: 2018/8/4
 * </pre>
 * <p>
 * </p>
 */
public class BaseReq implements Serializable{
    private static final long serialVersionUID = -1303894215211299117L;

    public String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
