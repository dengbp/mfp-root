package com.yr.net.repository;

import com.yr.net.entity.PartyApply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

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

    /**
     * 根据id、类型查活动列表
     * @param id id
     * @param partyType partyType
     * @return list party
     */
     List<PartyApply> findByIdAndPartyType(Long id,Integer partyType);
}
