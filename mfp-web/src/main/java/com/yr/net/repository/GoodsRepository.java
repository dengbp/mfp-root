package com.yr.net.repository;

import com.yr.net.entity.Follow;
import com.yr.net.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * All rights Reserved, Designed By HQYG
 * <pre>
 * Copyright:  Copyright(C) 2018
 * Company:    HQYG.
 * @Author:     dengbp
 * @Date: 2018/4/27
 * </pre>
 * <p>
 *     用户评论关系dao
 * </p>
 */
public interface GoodsRepository extends JpaRepository<Goods, Long>,JpaSpecificationExecutor<Goods>{

    /**
     * 根据商品类型查商品信息
     * @param typeId 商品类型id
     * @return 商品信息
     */
    List<Goods> findByTypeId(Long typeId);

    /**
     * 根据商品类型查商品信息
     * @param typeIds 商品类型id
     * @return 商品信息
     */
    List<Goods> findByTypeIdIn(Long[] typeIds);

}
