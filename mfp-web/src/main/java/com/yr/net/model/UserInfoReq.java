package com.yr.net.model;

import lombok.Data;

/**
 * All rights Reserved, Designed By SEGI
 * <pre>
 * Copyright:  Copyright(C) 2018
 * Company:    SEGI.
 * @Author:     dengbp
 * @Date: 2018/7/27
 * </pre>
 * <p>
 *    用户信息
 * </p>
 */
@Data
public class UserInfoReq {

    private String username;

    private String password;

    private String phone;

    private String messageCode;
    /**
     * 短信验证码有效期信息
     */
    private String codeTime;


    public UserInfoReq() {
    }

    public UserInfoReq(String phone, String messageCode, String codeTime) {
        this.phone = phone;
        this.messageCode = messageCode;
        this.codeTime = codeTime;
    }

    public UserInfoReq(String username, String password) {
        this.username = username;
        this.password = password;
    }


}
