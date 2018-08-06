package com.yr.net.service.impl;

import com.yr.net.entity.Enroll;
import com.yr.net.entity.PartyApply;
import com.yr.net.model.PartyApplyReq;
import com.yr.net.repository.EnrollRepository;
import com.yr.net.repository.PartyRepository;
import com.yr.net.util.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Optional;

/**
 * All rights Reserved, Designed By SEGI
 * <pre>
 * Copyright:  Copyright(C) 2018
 * Company:    SEGI.
 * @Author:     dengbp
 * @Date: 2018/5/30
 * </pre>
 * <p>
 *     活动报名服务类
 * </p>
 */
@Service
public class PartyService {
    Logger logger = LoggerFactory.getLogger(PartyService.class);
    @Resource
    private EnrollRepository enrollRepository;
    @Resource
    private PartyRepository partyRepository;

    /**
     * 活动报名
     * @param enroll 报名信息
     * @return 是否报名成功 true：成功，false:已报名
     */
    public boolean saveOrUpdate(Enroll enroll) {
        Optional<Enroll> optional = enrollRepository.findByPartyCodeAndPhone(enroll.getPartyCode(),enroll.getPhone());
        if(optional.isPresent()){
            return false;
        }
        enrollRepository.save(enroll);
        return true;
    }

    /**
     * 保存活动发布
     * @param partyApplyReq
     * @throws Exception
     */
    public void save(PartyApplyReq partyApplyReq) throws Exception {
        PartyApply partyApply = partyApplyReq;
        if (StringUtils.isNotBlank(partyApplyReq.getBeginTimeStr())){
            Date beginTime = DateUtil.getdate1(partyApplyReq.getBeginTimeStr());
            partyApply.setBeginTime(beginTime);
        }
        if (StringUtils.isNotBlank(partyApplyReq.getEndTimeStr())){
            Date endTime = DateUtil.getdate1(partyApplyReq.getEndTimeStr());
            partyApply.setEndTime(endTime);
        }
        if (StringUtils.isNotBlank(partyApplyReq.getConductTimeStr())){
            Date condUctTime = DateUtil.getdate1(partyApplyReq.getConductTimeStr());
            partyApply.setConductTime(condUctTime);
        }
        partyApply.setCreateTime(new Date());
        partyRepository.save(partyApplyReq);
    }
}
