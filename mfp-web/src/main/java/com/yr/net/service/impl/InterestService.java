package com.yr.net.service.impl;

import com.yr.net.entity.Interest;
import com.yr.net.repository.InterestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * All rights Reserved, Designed By SEGI
 * <pre>
 * Copyright:  Copyright(C) 2018
 * Company:    SEGI.
 * Author:     dengbp
 * CreateDate: 2018/5/28
 * </pre>
 * <p>
 *     爱好服务类
 * </p>
 */
@Service
public class InterestService {
    Logger logger = LoggerFactory.getLogger(InterestService.class);
    @Resource
    private InterestRepository interestRepository;

    /**
     * 获取爱好列表
     * @return 爱好列表
     */
    public List<Interest> findAll(){
        return interestRepository.findAll();
    }
}
