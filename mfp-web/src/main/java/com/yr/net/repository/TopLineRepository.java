package com.yr.net.repository;

import com.yr.net.entity.TopLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

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
 *     上伊人头条dao
 * </p>
 */
public interface TopLineRepository extends JpaRepository<TopLine, Long>,JpaSpecificationExecutor<TopLine> {

    /**
     * 根据条件取上头条信息
     * @param userId 用户id
     * @param openId 微信openid
     * @return 头条信息
     */
    List<TopLine> findByUserIdOrOpenId(Long userId,String openId);
}
