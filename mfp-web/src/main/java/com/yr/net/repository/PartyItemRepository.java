package com.yr.net.repository;

import com.yr.net.entity.PartyItem;
import com.yr.net.entity.PartyTheme;
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
public interface PartyItemRepository extends JpaRepository<PartyItem, Long>,JpaSpecificationExecutor<PartyItem> {

    /**
     * 根据约会、活动类型id取约会项目
     * @param themeId
     * @return
     */

    List<PartyItem> findByThemeId(Long themeId);

}
