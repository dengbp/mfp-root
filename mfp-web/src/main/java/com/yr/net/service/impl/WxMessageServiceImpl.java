package com.yr.net.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.yr.net.http.HttpUtils;
import com.yr.net.model.CustomMessage;
import com.yr.net.service.AccessTokenService;
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
        logger.info("开始发送支付成功通知消息");
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

    @Override
    public void sendPartyMsg(Map<String, String> mapData) throws ParseException {
        logger.info("开始发送约会通知消息");
        Date visitDate = new Date();
        int i = (int)(5+Math.random()*(20-5+1));
        String endDate = DateFormatUtils.format(new Date(visitDate.getTime()+i*60*1000), "yyyy-MM-dd HH:mm:ss");

        TemplateMessage templateMessage = new TemplateMessage();
        templateMessage.setTemplate_id("JoTwHkfS0KvDPDJ1lO_oBbqZvtveI5odxZ2jY0c4m6U");
        templateMessage.setTouser(mapData.get("openid"));
        templateMessage.setUrl(mapData.get("link"));
        //模板消息标题
        LinkedHashMap<String, TemplateMessageItem> messageDataMap = new LinkedHashMap<>();
        messageDataMap.put("first", genTemplateItem(mapData.get("first"), "#173177"));
        //预约人员
        messageDataMap.put("keyword1",genTemplateItem(mapData.get("userName")));
        //预约类型
        messageDataMap.put("keyword2",  genTemplateItem("约会", "#173177"));
        //预约理由
        messageDataMap.put("keyword3", genTemplateItem(mapData.get("reason")));
        //访问时间
        messageDataMap.put("keyword4", genTemplateItem(mapData.get("partyTime"), "#173177"));
        //结束时间
        messageDataMap.put("keyword5", genTemplateItem(endDate, "#173177"));
        //备注
        messageDataMap.put("remark", genTemplateItem(mapData.get("remarks")));
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
