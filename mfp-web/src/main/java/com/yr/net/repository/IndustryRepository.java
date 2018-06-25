package com.yr.net.repository;

import com.yr.net.entity.Industry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Stream;

/**
 * All rights Reserved, Designed By SEGI
 * <pre>
 * Copyright:  Copyright(C) 2018
 * Company:    SEGI.
 * Author:     dengbp
 * CreateDate: 2018/5/28
 * </pre>
 * <p>
 *    行业类型dao
 * </p>
 */
public interface IndustryRepository extends JpaRepository<Industry,Long> {

    /**
     * 根据行业类型编码去重
     * @return 去重后的行业列表
     */
    @Query("select u from yr_industry u group by u.type")
    List<Industry> findAllByIndustryQueryAndStream();

    /**
     * 根据行业获取职位
     * @param type
     * @return
     */
    List<Industry> findByType(Integer type);
}
