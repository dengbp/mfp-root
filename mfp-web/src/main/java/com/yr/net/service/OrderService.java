package com.yr.net.service;

import com.yr.net.entity.OrderEntity;
import com.yr.net.model.OrderReq;

import java.util.List;

/**
 * 订单Service
 */
public interface OrderService {
    /**
     * 创建订单
     * @param orderReq
     * @return
     */
    OrderEntity createOrder(OrderReq orderReq);
    /**
     * 修改订单状态Wie支付成功
     */
    OrderEntity updateState(String orderId);

    /**
     * 查询订单
     * @param orderId
     * @return
     */
    OrderEntity findByOrderId(String orderId);

    /**
     * 根据用户id取定单
     * @param userId
     * @param state 订单状态
     * @return 订单信息
     */
    List<OrderEntity> findByUserId(Integer userId,Byte state);
}
