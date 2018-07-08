package com.yr.net.model;

import lombok.Data;

import java.io.Serializable;

/**
 * All rights Reserved, Designed By SEGI
 * <pre>
 * Copyright:  Copyright(C) 2018
 * Company:    SEGI.
 * @Author: dengbp
 * @Date: 2018/7/5
 * </pre>
 * <p>
 *     客服自定义消息
 * </p>
 */
@Data
public class CustomMessage implements Serializable{

    /**
     * 用户openId
     */
    private String openId;
    /**
     * msgtype消息类型
     */
    private String messageType;

    /**
     * 消息
     */
    private Object news;


}
