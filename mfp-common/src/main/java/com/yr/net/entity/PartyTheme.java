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
 *     约会主题映射表
 * </p>
 */
@Data
@Entity(name="yr_party_theme")
public class PartyTheme implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 约会、活动主题类型：0：约会；1：活动
     */
    private Integer themeType;
    /**
     * 约会主题名称
     */
    private String themeName;

    private Date createTime;

}
