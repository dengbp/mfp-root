package com.yr.net.service;

import com.yr.net.model.OrderReq;

public interface WxPayService {
    String unifiedorder(OrderReq orderReq);
}
