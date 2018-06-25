package com.yr.net.bean;

import lombok.Getter;
import lombok.Setter;

/**
 * All rights Reserved, Designed By SEGI
 * <pre>
 * Copyright:  Copyright(C) 2018
 * Company:    SEGI.
 * Author:     dengbp
 * CreateDate: 2018/6/20
 * </pre>
 * <p>
 *     token信息
 * </p>
 */
@Setter
@Getter
public class GeneralToken {
    private long expiresIn; //成功有效时间
    private String accessToken;  // 普通Token
    private String errorCode; //失败ID
    private String errMessage; //失败消息
    public static GeneralToken instance = new GeneralToken();
    private GeneralToken(){

    }
}
