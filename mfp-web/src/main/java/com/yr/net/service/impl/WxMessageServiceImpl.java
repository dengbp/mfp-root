package com.yr.net.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.yr.net.http.HttpUtils;
import com.yr.net.model.CustomMessage;
import com.yr.net.service.WxMessageService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weixin.popular.api.MessageAPI;
import weixin.popular.bean.message.templatemessage.TemplateMessage;
import weixin.popular.bean.message.templatemessage.TemplateMessageItem;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class WxMessageServiceImpl implements WxMessageService {
    private static final Logger logger = LoggerFactory.getLogger(WxMessageServiceImpl.class);
    private final AccessTokenService accessTokenService;

    @Autowired
    public WxMessageServiceImpl(AccessTokenService accessTokenService) {
        this.accessTokenService = accessTokenService;
    }

    @Override
    public void sendBuySuccessMsg(Map<String, String> mapData) throws ParseException {
        logger.info("开始发送模板消息");
        String orderId = mapData.get("out_trade_no");
        TemplateMessage templateMessage = new TemplateMessage();
        templateMessage.setTemplate_id("UXdVimzD7liALwhL_vuH1ym_BbJeF5hfaY4ZBlUi3VY");
        templateMessage.setTouser(mapData.get("openid"));
        //模板消息内容
        LinkedHashMap<String, TemplateMessageItem> messageDataMap = new LinkedHashMap<>();
        messageDataMap.put("first", genTemplateItem("恭喜您支付成功！", "#173177"));
        Date payDate = DateUtils.parseDate(mapData.get("time_end"), new String[]{"yyyyMMddHHmmss"});
        //支付时间
        messageDataMap.put("keyword1", genTemplateItem(DateFormatUtils.format(payDate, "yyyy-MM-dd HH:mm:ss"), "#173177"));
//            OrderEntity orderEntity = orderService.findByOrderId(orderId);
        //商品名称
        messageDataMap.put("keyword2", genTemplateItem(mapData.get("attach")));
        //订单金额
        messageDataMap.put("keyword3", genTemplateItem(Long.parseLong(mapData.get("total_fee"))/100+""));
        //订单号
        messageDataMap.put("keyword4", genTemplateItem(orderId));
        //商家
        messageDataMap.put("keyword5", genTemplateItem("深圳伊人网网络有限公司"));
        //备注
        messageDataMap.put("remark", genTemplateItem("祝您早日寻到TA！"));
        templateMessage.setData(messageDataMap);
        MessageAPI.messageTemplateSend(accessTokenService.getGlobalToken(), templateMessage);
    }

    /**
     * 客服消息推送
     * @param customMessage customMessage
     * @param serverUrl serverUrl
     * @param accessToken
     * @return 第三方返回结果
     */
    @Override
    public String customSend(CustomMessage customMessage, String serverUrl,String accessToken) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("touser",customMessage.getOpenId());
        jsonObject.put("msgtype",customMessage.getMessageType());
        jsonObject.put("news",customMessage.getNews());
        String url = serverUrl.replace("ACCESS_TOKEN",accessToken);
        logger.info("消息推送url【{}】",url);
        logger.info("消息推送报文【{}】",jsonObject.toString());
        String result = HttpUtils.sendByPost(url,jsonObject.toString());
        logger.info("响应结果【{}】",result);
        return result;
    }

    private TemplateMessageItem genTemplateItem(String value) {
        return this.genTemplateItem(value, null);
    }

    private TemplateMessageItem genTemplateItem(String value, String color) {
        TemplateMessageItem item = new TemplateMessageItem();
        item.setValue(value);
        if (StringUtils.isBlank(color)){
            color = "#173177";
        }
        item.setColor(color);
        return item;
    }
}
