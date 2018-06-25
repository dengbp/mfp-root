package com.yr.net.entity;

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
@Setter
@Getter
@Entity(name="yr_enroll")
public class Enroll implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date createTime;//报名时间
    private String partyCode;//参加的活动编号
    private String phone;
    private Integer sex;//1男2女
    private String wechatNickName;//微信昵称
    private Integer payDeposit;//是否已付定金 1是2否
    private Integer payAll;//是否已全部交报名费 1是2否
    private String remarks;//备注
    private int isAgreement;//是否毁约  1是2否3待定
}
