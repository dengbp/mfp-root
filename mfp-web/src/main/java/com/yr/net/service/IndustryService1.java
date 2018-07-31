package com.yr.net.service;

import com.yr.net.entity.Industry;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * All rights Reserved, Designed By SEGI
 * <pre>
 * Copyright:  Copyright(C) 2018
 * Company:    SEGI.
 * @Author: dengbp
 * @Date: 2018/7/31
 * </pre>
 * <p>
 * </p>
 */
public interface IndustryService1 {

    /**
     * 获取所有职务
     * @return 职务列表
     * @param type 行业类型
     * @return 职位信息列表
     */
    public List<Industry> getJobTitle(Integer type);

    /**
     * 根据行业类型编码去重
     * @return 去重后的行业列表
     */
    @Transactional
    public List<Industry> getIndustry();

    /**
     * 根据id获取职业信息
     * @param id id
     * @return 职业信息
     */
    public Industry queryIndustryById(Long id);
}
