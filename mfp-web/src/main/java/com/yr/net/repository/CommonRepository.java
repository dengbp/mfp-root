package com.yr.net.repository;

import com.yr.net.entity.Common;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * All rights Reserved, Designed By HQYG
 * <pre>
 * Copyright:  Copyright(C) 2018
 * Company:    HQYG.
 * Author:     dengbp
 * CreateDate: 2018/4/26
 * </pre>
 * <p>
 *     评论dao
 * </p>
 */
public interface CommonRepository extends JpaRepository<Common, Long>,JpaSpecificationExecutor<Common> {
}
