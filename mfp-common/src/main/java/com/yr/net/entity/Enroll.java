package com.yr.net.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity(name="yr_enroll")
public class Enroll implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String partyCode;

    private Integer partyId;

    private String partyName;

    private Integer userId;

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

    private String remarks;

    private Integer isAgreement;

    private Date createTime;

    private static final long serialVersionUID = 1L;
}