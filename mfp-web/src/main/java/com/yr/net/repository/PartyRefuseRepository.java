package com.yr.net.repository;

import com.yr.net.entity.PartyApply;
import com.yr.net.entity.PartyRefuse;
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
 *     约会拒绝理由dao
 * </p>
 */
public interface PartyRefuseRepository extends JpaRepository<PartyRefuse, Long>,JpaSpecificationExecutor<PartyRefuse> {

    /**
     * 取拒绝信息
     * @param typeId
     * @return
     */
    List<PartyRefuse> findByTypeId(Integer typeId);
}
