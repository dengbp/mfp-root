package com.yr.net.service.impl;

import com.yr.net.entity.WXCustomer;
import com.yr.net.repository.WXCustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * All rights Reserved, Designed By SEGI
 * <pre>
 * Copyright:  Copyright(C) 2018
 * Company:    SEGI.
 * Author:     dengbp
 * CreateDate: 2018/6/15
 * </pre>
 * <p>
 *     微信用户基本信息服务类
 * </p>
 */
@Service
public class WXCustomerService {
    Logger logger = LoggerFactory.getLogger(WXCustomerService.class);

    @Resource
    WXCustomerRepository wxCustomerRepository;

    /**
     * 保存用户信息
     * @param wxCustomer
     */
    public void save(WXCustomer wxCustomer){
        wxCustomerRepository.save(wxCustomer);
    }

    /**
     * 根据openid获取用户信息
     * @param openId
     * @return
     */
    public WXCustomer findById(String openId){
        return wxCustomerRepository.findByOpenId(openId);
    }
}
