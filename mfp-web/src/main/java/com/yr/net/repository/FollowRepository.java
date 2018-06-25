package com.yr.net.repository;

import com.yr.net.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * All rights Reserved, Designed By HQYG
 * <pre>
 * Copyright:  Copyright(C) 2018
 * Company:    HQYG.
 * Author:     dengbp
 * CreateDate: 2018/4/27
 * </pre>
 * <p>
 *     用户评论关系dao
 * </p>
 */
public interface FollowRepository extends JpaRepository<Follow, Long>,JpaSpecificationExecutor<Follow>{
}
