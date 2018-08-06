package com.yr.net.repository;

import com.yr.net.entity.PartyApply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * All rights Reserved, Designed By SEGI
 * <pre>
 * Copyright:  Copyright(C) 2018
 * Company:    SEGI.
 * @Author: dengbp
 * @Date: 2018/8/6
 * </pre>
 * <p>
 *     活动dao
 * </p>
 */
public interface PartyRepository  extends JpaRepository<PartyApply, Long>,JpaSpecificationExecutor<PartyApply> {
}
