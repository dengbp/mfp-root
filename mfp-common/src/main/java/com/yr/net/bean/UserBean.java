package com.yr.net.bean;

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
public class UserBean {
    private String username;

    private String password;

    private String role;

    private String permission;

}
