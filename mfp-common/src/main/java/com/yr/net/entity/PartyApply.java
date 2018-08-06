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
 * @Author:     dengbp
 * @Date: 2018/5/28
 * </pre>
 * <p>
 *     活动约会实体映射表
 * </p>
 */
@Data
@Entity(name="yr_party_apply")
public class PartyApply implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 活动主题、活动名称
     */
    private String name;

    /**
     * 活动内容
     */
    private String partyContent;

    /**
     * 活动方式id（从活动方式表来）
     */
    private Integer partyTypeId;

    /**
     * 活动方式名称
     */
    private String partyTypeName;

    /**
     * 可报名人数
     */
    private Integer enrollMax;

    /**
     * 男生人数
     */
    private Integer maleMax;

    /**
     * 女生人数
     */
    private Integer femaleMax;

    /**
     * 举办地址
     */
    private String conductAddr;

    /**
     * 举办时间
     */
    private Date conductTime;

    /**
     * 开始报名时间
     */
    private Date beginTime;

    /**
     * 报名结束时间
     */
    private Date endTime;

    /**
     * 诚意金id
     */
    private Integer goodsId;

    /**
     * 诚意金金额
     */
    private Integer price;

    /**
     * 报名费（单位分）
     */
    private Integer entryFee;

    /**
     * 发布者id
     */
    private Long publisherId;

    /**
     * 发布者
     */
    private String publisher;

    /**
     * 活动状态（0：申请审核中，1：执行中，2：已取消，3：审核不通过，4：已正常结束）
     */
    private Long status;

    /**
     * 备注说明活动要求等信息
     */
    private String remarks;

    private Date createTime;

    /**
     * 活动类型：1：约会；2：活动
     */
    private Integer partyType;

    private static final long serialVersionUID = 1L;

}