package com.yr.net.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * All rights Reserved, Designed By SEGI
 * <pre>
 * Copyright:  Copyright(C) 2018
 * Company:    SEGI.
 * @Author: dengbp
 * @Date: 2018/7/5
 * </pre>
 * <p>
 *     上头条申请实体
 * </p>
 */
@Data
@Entity(name="yr_top_line")
public class TopLine implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户微信openId
     */
    private String openId;
    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 性别。1男2女
     */
    private Integer sex;
    /**
     * 用户手机
     */
    private String userPhone;
    /**
     * 申请上头条备注
     */
    private String remarks;
    /**
     * 头条相片
     */
    private String topLinePic;
    /**
     * 申请时间
     */
    private Date createTime;

}
