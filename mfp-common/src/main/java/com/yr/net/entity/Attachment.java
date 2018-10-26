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
 * CreateDate: 2018/6/13
 * </pre>
 * <p>
 *     附件实体类
 * </p>
 */
@Setter
@Getter
@Entity(name="yr_attachment")
public class Attachment implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long uploadUserId;
    private Long businessId;
    private Integer businessType;
    private String uploadName;//附件名称
    private String uploadUser;//上传人
    private Date uploadTime;//上传时间
    private Long orginSize;//原附件大小
    private String suffix;//附件文件类型(后缀)
    private String url;//存储文件url
    private String orginName;//上传附件文件原名称
    private Long storeSize;//落地存储实际大小
    private Integer imageWith;
    private Integer imageHeight;
    private Integer fileType;//文件类型1:图片；2:视频
    private String remarks;//备注
    private Date createTime;//记录创建时间
    private Date updateTime;//被修改时间
    private String thumbnail;//视频缩略图
}
