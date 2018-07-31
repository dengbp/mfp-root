package com.yr.net.service.impl;

import com.yr.net.entity.Industry;
import com.yr.net.repository.IndustryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * All rights Reserved, Designed By SEGI
 * <pre>
 * Copyright:  Copyright(C) 2018
 * Company:    SEGI.
 * @Author:     dengbp
 * @Date: 2018/5/28
 * </pre>
 * <p>
 *     行业服务类
 * </p>
 */
@Service
public class IndustryService1Impl implements com.yr.net.service.IndustryService1{
    Logger logger = LoggerFactory.getLogger(IndustryService1Impl.class);
    @Resource
    private IndustryRepository industryRepository;

    /**
     * 获取所有职务
     * @return 职务列表
     * @param type 行业类型
     * @return 职位信息列表
     */
    @Override
    public List<Industry> getJobTitle(Integer type){
        return industryRepository.findByType(type);
    }

    /**
     * 根据行业类型编码去重
     * @return 去重后的行业列表
     */
    @Transactional
    @Override
    public List<Industry> getIndustry(){
        return industryRepository.findAllByIndustryQueryAndStream();
    }

    /**
     * 根据id获取职业信息
     * @param id id
     * @return 职业信息
     */
    @Override
    public Industry queryIndustryById(Long id){
        Optional optional = industryRepository.findById(id);
        if(optional.isPresent()){
            Industry industry = (Industry) optional.get();
            return industry;
        }
        return null;
    }
}
