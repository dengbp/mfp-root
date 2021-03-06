package com.yr.net.service;

import com.yr.net.entity.Goods;
import com.yr.net.repository.GoodsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * All rights Reserved, Designed By SEGI
 * <pre>
 * Copyright:  Copyright(C) 2018
 * Company:    SEGI.
 * @Author: dengbp
 * @Date: 2018/6/29
 * </pre>
 * <p>
 *     商品服务类
 * </p>
 */
@Service
public class GoodsService {
    Logger logger = LoggerFactory.getLogger(CharacterService.class);
    @Resource
    private GoodsRepository goodsRepository;

    /**
     * 根据商品类型查商品信息
     * @param typeId 商品类型id
     * @return 商品信息
     */
    public List<Goods> findByTypeId(Long typeId){
        return goodsRepository.findByTypeId(typeId);
    }

    /**
     * 根据商品类型查商品信息
     * @param typeIds 商品类型id
     * @return 商品信息
     */
    public List<Goods> findByIdTypeIds(Long[] typeIds){
        return goodsRepository.findByTypeIdIn(typeIds);
    }

    /**
     * 根据商品id取商品信息
     * @param id 商品id
     * @return 商品信息
     */
    public Goods findById(Long id){
        Optional optional = goodsRepository.findById(id);
        if (optional.isPresent()){
            return (Goods)optional.get();
        }
        return null;
    }

    /**
     * 根据商品ids取商品信息
     * @param ids 商品id
     * @return 商品信息
     */
    public List<Goods> findByIds(List<Long> ids){
        Long[] longs =ids.stream().toArray(Long[]::new);
        return goodsRepository.findByIdIn(longs);
    }


}
