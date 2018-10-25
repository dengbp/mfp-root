package com.yr.net.model;

import lombok.Data;

/**
 * All rights Reserved, Designed By SEGI
 * <pre>
 * Copyright:  Copyright(C) 2018
 * Company:    SEGI.
 * @Author: dengbp
 * @Date: 2018/8/7
 * </pre>
 * <p>
 * </p>
 */
@Data
public class LoginResp extends BaseReq{

    public LoginResp(Long userId, Integer role,String token) {
        this.userId = userId;
        this.role = role;
        this.token = token;
    }

    private Long userId;

    /**
     * 角色。0：相亲；1：红娘；
     */
    private Integer role;

    /**
     * 性别 1:男;2:女
     */
    private Integer sex;
}
