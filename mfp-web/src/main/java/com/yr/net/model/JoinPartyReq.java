package com.yr.net.model;

import lombok.Data;

import java.io.Serializable;

/**
 * All rights Reserved, Designed By SEGI
 * <pre>
 * Copyright:  Copyright(C) 2018
 * Company:    SEGI.
 * @Author: dengbp
 * @Date: 2018/7/3
 * </pre>
 * <p>
 *     约会、活动报名入参对象
 * </p>
 */
@Data
public class JoinPartyReq implements Serializable{

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 微信用户id
     */
    private String openId;
    /**
     * 活动id
     */
    private Long partyId;
    /**
     * 活动编码
     */
    private String partyCode;
//    /**
//     * 是否已支付 0：未支付；1:已支付
//     */
//    private Integer isPay;
}
