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
 *     性格实体映射表
 * </p>
 */
@Data
@Entity(name="yr_party_apply")
public class PartyApply implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String partyContent;

    private Integer partyTypeId;

    private String partyTypeName;

    private Integer enrollMax;

    private Integer maleMax;

    private Integer femaleMax;

    private String conductAddr;

    private Date conductTime;

    private Date beginTime;

    private Date endTime;

    private Integer entryFee;

    private Long publisherId;

    private String publisher;

    private Long status;

    private String remarks;

    private Date createTime;

    private static final long serialVersionUID = 1L;

}