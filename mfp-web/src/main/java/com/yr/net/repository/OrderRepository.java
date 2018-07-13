package com.yr.net.repository;

import com.yr.net.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 订单dao
 */
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {

    /**
     * 根据订单id取信息
     * @param orderId  orderId
     * @return 订单信息
     */
    public List<OrderEntity> findByOrderId(String orderId);

    /**
     * 根据订单id取信息
     * @param userId
     * @param orderStat 支付状态
     * @return 订单信息
     */
    public List<OrderEntity> findByUserIdAndOrderStat(Integer userId,Byte orderStat);
}
