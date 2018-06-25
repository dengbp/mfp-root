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
 *     行业职务实体映射表
 * </p>
 */
@Setter
@Getter
@Entity(name="yr_industry")
public class Industry implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String industry;//行业名称
    private String jobTitle;//职务名称
    private String remarks;//备注
    private Integer type;//行业类型编码
    private Date createTime;

}
