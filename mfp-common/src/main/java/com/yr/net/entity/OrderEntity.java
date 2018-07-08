package com.yr.net.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "yr_order")
public class OrderEntity {
    private int id;
    private String orderId;
    private Integer userId;
    private Integer orderGoodId;
    private Date orderTime;
    private BigDecimal orderMoney;
    private String hygPhone;
    private Byte orderStat;
    private Byte orderExp;
    private Byte orderPay;
    private String orderMes;
    private Date payTime;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "order_id")
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "order_good_id")
    public Integer getOrderGoodId() {
        return orderGoodId;
    }

    public void setOrderGoodId(Integer orderGoodId) {
        this.orderGoodId = orderGoodId;
    }

    @Basic
    @Column(name = "order_time")
    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    @Basic
    @Column(name = "order_money")
    public BigDecimal getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(BigDecimal orderMoney) {
        this.orderMoney = orderMoney;
    }

    @Basic
    @Column(name = "hyg_phone")
    public String getHygPhone() {
        return hygPhone;
    }

    public void setHygPhone(String hygPhone) {
        this.hygPhone = hygPhone;
    }

    @Basic
    @Column(name = "order_stat")
    public Byte getOrderStat() {
        return orderStat;
    }

    public void setOrderStat(Byte orderStat) {
        this.orderStat = orderStat;
    }

    @Basic
    @Column(name = "order_exp")
    public Byte getOrderExp() {
        return orderExp;
    }

    public void setOrderExp(Byte orderExp) {
        this.orderExp = orderExp;
    }

    @Basic
    @Column(name = "order_pay")
    public Byte getOrderPay() {
        return orderPay;
    }

    public void setOrderPay(Byte orderPay) {
        this.orderPay = orderPay;
    }

    @Basic
    @Column(name = "order_mes")
    public String getOrderMes() {
        return orderMes;
    }

    public void setOrderMes(String orderMes) {
        this.orderMes = orderMes;
    }

    @Basic
    @Column(name = "pay_time")
    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderEntity that = (OrderEntity) o;

        if (id != that.id) return false;
        if (orderId != null ? !orderId.equals(that.orderId) : that.orderId != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (orderGoodId != null ? !orderGoodId.equals(that.orderGoodId) : that.orderGoodId != null) return false;
        if (orderTime != null ? !orderTime.equals(that.orderTime) : that.orderTime != null) return false;
        if (orderMoney != null ? !orderMoney.equals(that.orderMoney) : that.orderMoney != null) return false;
        if (hygPhone != null ? !hygPhone.equals(that.hygPhone) : that.hygPhone != null) return false;
        if (orderStat != null ? !orderStat.equals(that.orderStat) : that.orderStat != null) return false;
        if (orderExp != null ? !orderExp.equals(that.orderExp) : that.orderExp != null) return false;
        if (orderPay != null ? !orderPay.equals(that.orderPay) : that.orderPay != null) return false;
        if (orderMes != null ? !orderMes.equals(that.orderMes) : that.orderMes != null) return false;
        if (payTime != null ? !payTime.equals(that.payTime) : that.payTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (orderId != null ? orderId.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (orderGoodId != null ? orderGoodId.hashCode() : 0);
        result = 31 * result + (orderTime != null ? orderTime.hashCode() : 0);
        result = 31 * result + (orderMoney != null ? orderMoney.hashCode() : 0);
        result = 31 * result + (hygPhone != null ? hygPhone.hashCode() : 0);
        result = 31 * result + (orderStat != null ? orderStat.hashCode() : 0);
        result = 31 * result + (orderExp != null ? orderExp.hashCode() : 0);
        result = 31 * result + (orderPay != null ? orderPay.hashCode() : 0);
        result = 31 * result + (orderMes != null ? orderMes.hashCode() : 0);
        result = 31 * result + (payTime != null ? payTime.hashCode() : 0);
        return result;
    }
}
