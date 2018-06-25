package com.yr.net.repository;

import com.yr.net.entity.Article;
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
 *     文章dao
 * </p>
 */
public interface ArticleRepository extends JpaRepository<Article, Long>,JpaSpecificationExecutor<Article>{
}
