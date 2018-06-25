package com.yr.net.repository;

import com.yr.net.entity.WXCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * All rights Reserved, Designed By SEGI
 * <pre>
 * Copyright:  Copyright(C) 2018
 * Company:    SEGI.
 * Author:     dengbp
 * CreateDate: 2018/6/15
 * </pre>
 * <p>
 *     微信用户基本信息dao
 * </p>
 */
public interface WXCustomerRepository  extends JpaRepository<WXCustomer, Long>,JpaSpecificationExecutor<WXCustomer> {

    public WXCustomer findByOpenId(String openId);
}
