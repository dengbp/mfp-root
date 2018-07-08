package com.yr.net.service.impl;

import com.yr.net.entity.Goods;
import com.yr.net.repository.GoodsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    public List<Goods> findByTypeId(Long typeId){
        return goodsRepository.findByTypeId(typeId);
    }
}
