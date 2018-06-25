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
 *     评论表
 * </p>
 */
@Setter
@Getter
@Entity(name="yr_common")
public class Common implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long ownerUserId;//评论的用户id
    private Long targetUserId;//评论的目标用户id(该目标一般是用说的评论内容)
    private String content; //评论内容
    private Integer likeCount;//该评论被点赞的数量
    private Date createTime;
    private Long parentId;//评论的目标id(文章id或用户发表说说id)
    private Integer parentType;//文章类型
}
