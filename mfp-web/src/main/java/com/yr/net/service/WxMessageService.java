package com.yr.net.service;

import com.yr.net.model.CustomMessage;

import java.text.ParseException;
import java.util.Map;

public interface WxMessageService {

    /**
     * 支付成功通知
     * @param mapData mapData
     * @throws ParseException ParseException
     */
    void sendBuySuccessMsg(Map<String,String> mapData) throws ParseException;

    /**
     * 客服消息推送
     * @param customMessage customMessage
     * @param serverUrl serverUrl
     * @param accessToken
     * @return 第三方返回结果
     */
    String customSend(CustomMessage customMessage, String serverUrl,String accessToken);
}
