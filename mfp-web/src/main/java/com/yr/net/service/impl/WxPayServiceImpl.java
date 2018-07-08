package com.yr.net.service.impl;

import com.yr.net.model.OrderReq;
import com.yr.net.service.ServiceException;
import com.yr.net.service.WxPayService;
import com.yr.net.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import weixin.popular.api.PayMchAPI;
import weixin.popular.bean.paymch.Unifiedorder;
import weixin.popular.bean.paymch.UnifiedorderResult;
import weixin.popular.util.PayUtil;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class WxPayServiceImpl implements WxPayService {
    private Logger logger = LoggerFactory.getLogger(WxPayServiceImpl.class);

    @Override
    public String unifiedorder(OrderReq orderReq) {
        String key = Constants.MCH_KEY;
        Unifiedorder unifiedorder = new Unifiedorder();
        unifiedorder.setAppid(Constants.APPID);
        unifiedorder.setMch_id(Constants.MCHID);
        unifiedorder.setNonce_str(UUID.randomUUID().toString().replace("-", ""));

        unifiedorder.setOpenid(orderReq.getOpenId());
        unifiedorder.setBody("伊人网-" + orderReq.getGoodsName());
        unifiedorder.setOut_trade_no(orderReq.getOrderId());
        //单位分
        unifiedorder.setTotal_fee(orderReq.getTotalFee().multiply(new BigDecimal(100)).toBigInteger().toString());
        unifiedorder.setSpbill_create_ip("127.0.0.1");//IP
        unifiedorder.setNotify_url(Constants.NOTIFY_URL);
        unifiedorder.setTrade_type("JSAPI");//JSAPI，NATIVE，APP，WAP
        unifiedorder.setAttach(orderReq.getGoodsName());
        //统一下单，生成预支付订单
        UnifiedorderResult unifiedorderResult = PayMchAPI.payUnifiedorder(unifiedorder,key);

        //@since 2.8.5  API返回数据签名验证
        if(unifiedorderResult.getSign_status() !=null && unifiedorderResult.getSign_status()){
            return PayUtil.generateMchPayJsRequestJson(unifiedorderResult.getPrepay_id(), Constants.APPID, key);
        } else {
            logger.error("统一下单失败 {}", unifiedorderResult);
            throw new ServiceException("统一下单失败");
        }
    }
}
