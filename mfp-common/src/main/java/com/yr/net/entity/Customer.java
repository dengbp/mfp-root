package com.yr.net.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * All rights Reserved, Designed By HQYG
 * <pre>
 * Copyright:  Copyright(C) 2018
 * Company:    HQYG.
 * Author:     dengbp
 * CreateDate: 2018/4/17
 * </pre>
 * <p>
 *     用户信息表
 * </p>
 */
@Setter
@Getter
@Entity(name="yr_customer")
public class Customer implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String password;
    private String idNumber;
    private String phone;
    private String finishSchool;//毕业院校
    /**
     * 0:中专以下;1:中专;2:大专;3:本科;
     * 4:研究生;5:硕士;6:博士;7:博士后
     */
    private Integer education;
    /**
     * 1:男;2:女
     */
    private Integer sex;
    private Integer age;
    private String specificSign;//个性签名
    private Double height;//身高
    private Double weight;//体重
    private Date birthDate;//出生日期 yyyy-MM-dd
    private String pic;//相片(json格式)
    private String video;//短视频(json格式)
    private String headPic;//头像照
    private String background;//背景图
    private String introduce;//自我介绍
    private String loverRequire;//征友要求
    private String characterType;//性格(从性格表取数据,多个性格类型用逗号分开)
    private String likeType;//喜欢类型(从性格表取数据,多个性格类型用逗号分开)
    private String interest;//爱好(从爱好表取数据,多个爱好用逗号分开)
    private String industryType;//行业类型(从行业信息表取数据)
    private String jobTitle;//职务(从行业信息表取数据)
    private Double annualSalary;//年薪
    private String addr;//住址
    private String companyAddr;//公司地址
    private String huseholdRegister;//户籍
    private String origin;//籍贯
    private Integer level;//1:普通用户;2:普通会员;3:高级会员
    private Integer state;//1:未婚;2:离异;3:丧偶
    private Integer loveTimes;//感情经历(1:代表一断，2：代表两断,...)
    private Boolean isOnline;//0：离线;1:在线
    private String comeFrom;//用户来源（指哪个第三方系统推荐过来）
    private String userId;//用户业务id（唯一，方便查用户信息使用，用些平台会展示该id）
    private BigDecimal longitude;//经度
    private BigDecimal latitude;//纬度
    private String weChartNickName;//微信昵称
    private Date updateTime;
    private Date createTime;
    private Date loginTime;
    private Integer loginTimes;
    private Date memberStartTime;//会员开始时间
    private Date memberEndTime;//会员结束时间
    private String openId;//微信用户id
    private Integer role;//角色。0：相亲；1：红娘

    public Customer(){

    }
}
