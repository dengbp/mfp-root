package com.yr.net.service.impl;

import com.yr.net.entity.TopLine;
import com.yr.net.repository.TopLineRepository;
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
 * @Author: dengbp
 * @Date: 2018/7/5
 * </pre>
 * <p>
 *     上伊人头条服务类
 * </p>
 */
@Service
public class TopLineService {
    private static Logger logger = LoggerFactory.getLogger(TopLineService.class);
    @Resource
    private TopLineRepository topLineRepository;

    public void save(TopLine topLine){
        topLineRepository.save(topLine);
    }

    public boolean signed(Long userId,String openId){
        List<TopLine> lines = topLineRepository.findByUserIdOrOpenId(userId,openId);
        return lines.isEmpty()?false:true;
    }
}
