package com.yr.net.service;

import com.yr.net.entity.Enroll;
import com.yr.net.entity.PartyApply;
import com.yr.net.model.PartyApplyReq;
import com.yr.net.repository.EnrollRepository;
import com.yr.net.repository.PartyRepository;
import com.yr.net.util.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
     * @param partyApplyReq 活动信息
     * @throws Exception 业务异常
     */
    public void save(PartyApplyReq partyApplyReq) throws Exception {
        if (StringUtils.isNotBlank(partyApplyReq.getBeginTimeStr())){
            Date beginTime = DateUtil.getdate1(partyApplyReq.getBeginTimeStr());
            partyApplyReq.setBeginTime(beginTime);
        }
        if (StringUtils.isNotBlank(partyApplyReq.getEndTimeStr())){
            Date endTime = DateUtil.getdate1(partyApplyReq.getEndTimeStr());
            partyApplyReq.setEndTime(endTime);
        }
        if (StringUtils.isNotBlank(partyApplyReq.getConductTimeStr())){
            Date condUctTime = DateUtil.getdate1(partyApplyReq.getConductTimeStr());
            partyApplyReq.setConductTime(condUctTime);
        }
        /**
         * 表示新增
         */
        if(partyApplyReq.getId()==null){
            partyApplyReq.setCreateTime(new Date());
        }
        partyRepository.save((PartyApply) partyApplyReq);
    }

    /**
     * 根据id、类型查活动列表
     * @param id id
     * @param partyType partyType
     * @return list party
     */
    public List<PartyApply> queryByType(Long id,Integer partyType){
        List<PartyApply> list = partyRepository.findByIdAndPartyType(id,partyType);
        if(list.isEmpty()){
            return new ArrayList<>();
        }
        return list;
    }
}
