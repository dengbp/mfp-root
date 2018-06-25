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
 *     说说表
 * </p>
 */
@Setter
@Getter
@Entity(name="yr_article")
public class Article  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;//题目
    private String content;//内容
    private String author;//作者
    private Long authorId;
    private Date createTime;
    private Integer readyCount;//阅读量
    private Integer likeCount;//点赞量
    private Integer type;//文章类型：1：感情；2：生活；3旅游;4:官方活动类型;5:用户相亲类型;6:商品广告
    private Integer state;//状态:1:申请中;2:审核不通过;3:显示中;4:已下架
    private Boolean disableComment;//是否可以评论,0：不可以;1:可以
}