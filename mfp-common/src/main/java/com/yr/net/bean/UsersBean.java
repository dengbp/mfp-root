package com.yr.net.bean;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * All rights Reserved, Designed By SEGI
 * <pre>
 * Copyright:  Copyright(C) 2018
 * Company:    SEGI.
 * Author:     dengbp
 * CreateDate: 2018/5/27
 * </pre>
 * <p>
 *     用于与前台页面交互的用户bean
 * </p>
 */
@Setter
@Getter
public class UsersBean {
    private Long id;
    private String userName;
    private String password;
    private String idNumber;//身份证号
    private String finishSchool;//毕业院校
    private Integer education;//0:中专以下;1:中专;2:大专;3:本科;4:研究生;5:硕士;6:博士;7:博士后
    private Integer age;
    private String specificSign;//个性签名
    private Double height;//身高
    private Double weight;//体重
    private String introduce;//自我介绍
    private String loverRequire;//征友要求
    private String characterType;//性格(从性格表取数据,多个性格类型用逗号分开)
    private String likeType;//喜欢类型(从类型表取数据,多个类型用逗号分开)
    private String interest;//爱好(从爱好表取数据,多个爱好用逗号分开)
    private String industryType;//行业类型(从行业信息表取数据)
    private String jobTitle;//职务(从行业信息表取数据)
    private Double annualSalary;//年薪
    private String addr;//住址
    private String companyAddr;//公司地址
    private String huseholdRegister;//户籍
    private String origin;//籍贯
    private Integer state;//1:未婚;2:离异;3:丧偶
    private Integer loveTimes;//感情经历(1:代表一断，2：代表两断,...)
    private String birthday;//生日
    private Integer sex;//性别 1:男;2:女


}
