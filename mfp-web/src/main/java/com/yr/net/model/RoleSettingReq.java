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
public class RoleSettingReq extends BaseReq{
     Long userId;
    /**
     * 角色。0：相亲；1：红娘
     */
    Integer role;
}
