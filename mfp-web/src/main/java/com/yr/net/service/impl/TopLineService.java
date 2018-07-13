package com.yr.net.service.impl;

import com.yr.net.entity.Goods;
import com.yr.net.entity.OrderEntity;
import com.yr.net.entity.TopLine;
import com.yr.net.enums.GoodsType;
import com.yr.net.repository.TopLineRepository;
import com.yr.net.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * All rights Reserved, Designed By SEGI
 * <pre>
 * Copyright:  Copyright(C) 2018
 * Company:    SEGI.
 * @Author: dengbp
 * @Date: 2018/7/5
 * </pre>
 * <p>
 *     上伊人头条服务类
 * </p>
 */
@Service
public class TopLineService {
    private static Logger logger = LoggerFactory.getLogger(TopLineService.class);
    @Resource
    private TopLineRepository topLineRepository;
    @Resource
    private OrderService orderService;
    @Resource
    private GoodsService goodsService;

    public void save(TopLine topLine){
        topLineRepository.save(topLine);
    }

    /**
     * 取用户上头条状态
     * @param userId 用户id
     * @param openId openid
     * @return 状态 0:未报名未支付;1:已报名未支付;2:已报名已支付
     */
    public Integer signed(Long userId,String openId){
        List<TopLine> lines = topLineRepository.findByUserIdOrOpenId(userId,openId);
        Integer state = 0;
        if(lines.isEmpty()){
            return state;
        }
        state = 1;
        List<OrderEntity> orderEntities = orderService.findByUserId(new Integer(userId.longValue()+""),Byte.valueOf("2"));
        if (!orderEntities.isEmpty()){
            List<Long> goodsIds = new ArrayList<>();
            orderEntities.forEach(orderEntity -> goodsIds.add(new Long(orderEntity.getOrderGoodId())));
            List<Goods> goods = goodsService.findByIds(goodsIds);
            Optional optional = goods.stream().filter(goods1 -> (goods1.getTypeId().longValue()==GoodsType.TOPLINE.getId().intValue()) || (goods1.getTypeId().longValue()==GoodsType.MEMBER.getId().intValue())).findFirst();
            if (optional.isPresent()){
                state = 2;
            }
        }
        return state;
    }
}
