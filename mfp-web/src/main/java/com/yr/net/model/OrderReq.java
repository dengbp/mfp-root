package com.yr.net.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 下单请求类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderReq {
    /**
     * 商品id
     */
    private Integer goodsId;
    /**
     * 商品名称
     */
    @NotNull
    private String goodsName;
    /**
     * 订单总支付金额
     */
    @NotNull
    private BigDecimal totalFee;
    /**
     * openId
     */
    @NotNull
    private String openId;
    /**
     * 订单id
     */
    private String orderId;
    /**
     * 用户id
     */
    @NotNull
    private Integer custId;
    /**
     * 手机号
     */
    private String hygPhone;
    /**
     * 留言
     */
    private String message;
    /**
     * ip
     */
    private String remoteIp;
}
