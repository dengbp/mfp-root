package com.yr.net.service.impl;

import com.yr.net.entity.OrderEntity;
import com.yr.net.model.OrderReq;
import com.yr.net.repository.OrderRepository;
import com.yr.net.service.OrderService;
import com.yr.net.service.ServiceException;
import com.yr.net.util.CommonUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderEntity createOrder(OrderReq orderReq) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId(CommonUtils.genOrderId(orderReq.getCustId()));
        orderEntity.setHygPhone(orderReq.getHygPhone());
        orderEntity.setOrderGoodId(orderReq.getGoodsId());
        orderEntity.setOrderMes(orderReq.getMessage());
        orderEntity.setOrderMoney(orderReq.getTotalFee());
        orderEntity.setOrderStat((byte) 1); //待支付
        orderEntity.setOrderTime(new Date());
        orderEntity.setOrderPay((byte) 1); //微信支付
        orderEntity.setUserId(orderReq.getCustId());
        return orderRepository.save(orderEntity);
    }

    @Override
    public OrderEntity updateState(String orderId) {
        OrderEntity updateOrder = this.findByOrderId(orderId);
        updateOrder.setOrderStat((byte) 2); //
        updateOrder.setPayTime(new Date());
        return orderRepository.save(updateOrder);
    }

    @Override
    public OrderEntity findByOrderId(String orderId) {
        if (StringUtils.isBlank(orderId)) {
            logger.error("订单Id获取失败");
            throw new ServiceException("订单Id获取失败");
        }
        List<OrderEntity> orderEntityList = orderRepository.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderEntityList)) {
            logger.error("未查询到订单    " + orderId);
            throw new ServiceException("未查询到订单");
        }
        if (orderEntityList.size() > 1) {
            logger.error("存在重复订单    " + orderId);
            throw new ServiceException("存在重复订单！ " + orderId);
        }
        return orderEntityList.get(0);
    }

    @Override
    public List<OrderEntity> findByUserId(Integer userId) {
        return orderRepository.findByUserId(userId);
}


}
