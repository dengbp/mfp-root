package com.yr.net.model;

import com.yr.net.entity.PartyApply;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * All rights Reserved, Designed By SEGI
 * <pre>
 * Copyright:  Copyright(C) 2018
 * Company:    SEGI.
 * @Author: dengbp
 * @Date: 2018/8/6
 * </pre>
 * <p>
 * </p>
 */
@Data
public class PartyApplyReq extends PartyApply{

    private String token;
    /**
     * 活动时间(yyyy-MM-dd HH:mm:ss)
     */
    private String conductTimeStr;
    /**
     * 开始报名时间(yyyy-MM-dd HH:mm:ss)
     */
    private String beginTimeStr;

    /**
     * 报名结束时间(yyyy-MM-dd HH:mm:ss)
     */
    private String endTimeStr;

    /**
     * 约ta理由
     */
    private String reason;
}
