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
 * All rights Reserved, Designed By HQYG
 * <pre>
 * Copyright:  Copyright(C) 2018
 * Company:    HQYG.
 * Author:     dengbp
 * CreateDate: 2018/4/26
 * </pre>
 * <p>
 *     关注关系表
 * </p>
 */
@Setter
@Getter
@Entity(name="yr_follow")
public class Follow implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;//被关注的用户id
    private Long followedUserId;//关注的人的id
    private Integer status;//关注状态:1:关注中，2：已取消关注
    private Date updateTime;
    private Date createTime;
}
