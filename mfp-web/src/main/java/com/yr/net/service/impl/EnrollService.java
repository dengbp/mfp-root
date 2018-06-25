package com.yr.net.service.impl;

import com.yr.net.entity.Enroll;
import com.yr.net.repository.EnrollRepository;
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
 * Author:     dengbp
 * CreateDate: 2018/5/30
 * </pre>
 * <p>
 *     活动报名服务类
 * </p>
 */
@Service
public class EnrollService {
    Logger logger = LoggerFactory.getLogger(EnrollService.class);
    @Resource
    private EnrollRepository enrollRepository;

    public boolean saveOrUpdate(Enroll enroll) {
        Optional<Enroll> optional = enrollRepository.findByPartyCodeAndPhone(enroll.getPartyCode(),enroll.getPhone());
        if(optional.isPresent())
            return false;
        enrollRepository.save(enroll);
        return true;
    }
}
