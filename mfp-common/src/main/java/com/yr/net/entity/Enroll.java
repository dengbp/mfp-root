package com.yr.net.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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
 * Author:     dengbp
 * CreateDate: 2018/5/28
 * </pre>
 * <p>
 *     参加活动报名表
 * </p>
 */
@Data
@Entity(name="yr_enroll")
public class Enroll implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 报名时间
     */
    private Date createTime;
    /**
     * 参加的活动编号
     */
    private String partyCode;
    private Long partyId;
    private Long userId;
    private String openId;
    private String phone;
    /**
     * 1男2女
     */
    private Integer sex;
    /**
     * 微信昵称
     */
    private String wechatNickName;
    /**
     * 是否已付定金 1是2否
     */
    private Integer payDeposit;
    /**
     * 是否已全部交报名费 1是2否
     */
    private Integer payAll;
    private String remarks;//备注
    /**
     * 是否毁约  1是2否3待定
     */
    private int isAgreement;
}
