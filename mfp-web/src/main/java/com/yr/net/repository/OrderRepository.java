package com.yr.net.repository;

import com.yr.net.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {

    public List<OrderEntity> findByOrderId(String orderId);
}
