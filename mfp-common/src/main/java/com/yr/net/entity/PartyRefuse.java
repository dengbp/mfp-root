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
@Entity(name="yr_refuse")
public class PartyRefuse implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 类型id
     */
    private Integer typeId;
    /**
     * 拒绝理由
     */
    private String reason;
}