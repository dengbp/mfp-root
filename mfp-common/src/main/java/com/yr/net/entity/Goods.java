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
 * @Author:     dengbp
 * @Date: 2018/5/28
 * </pre>
 * <p>
 *     性格实体映射表
 * </p>
 */
@Setter
@Getter
@Entity(name="yr_goods")
public class Goods implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String goodsName;//套餐名称
    private Long typeId;//套餐分类id
    private String typeName;//套餐类型名称
    private String goodsNo;//套餐编号
    private java.math.BigDecimal goodsPrice;//套餐价格
    private String remarks;//套餐说明，特权说明
    private Integer status;//套餐状态：0：可用；1不可用
    private Date createTime;

}
