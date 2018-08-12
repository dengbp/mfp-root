package com.yr.net.web.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yr.net.bean.AjaxResponse;
import com.yr.net.model.CustomMessage;
import com.yr.net.service.WxMessageService;
import com.yr.net.service.impl.AccessTokenService;
import com.yr.net.service.impl.WXCustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * All rights Reserved, Designed By SEGI
 * <pre>
 * Copyright:  Copyright(C) 2018
 * Company:    SEGI.
 * @Author: dengbp
 * @Date: 2018/7/5
 * </pre>
 * <p>
 *     客服接口消息控制器
 * </p>
 */
@RestController
@RequestMapping("/wx/custom/message")
public class CustomMessageController {
    private static Logger logger = LoggerFactory.getLogger(CustomMessageController.class);

    @Value("${wx.custom.interface.url}")
    private String url;
    private @Resource
    WxMessageService wxMessageService;
    @Resource
    private AccessTokenService accessTokenService;

    /**
     *
     * @param openId
     */
    @RequestMapping(method = RequestMethod.GET,path = "/send")
    public AjaxResponse send(String openId){
        CustomMessage customMessage = new CustomMessage();
        customMessage.setOpenId(openId);
        customMessage.setMessageType("news");
        JSONArray jsonArray = new JSONArray();
        int size = 8;
        for (int i=0;i<size;i++){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("title","刘丝丝"+i);
            jsonObject.put("description","漂亮美丽颜值高");
            jsonObject.put("url","https://mp.weixin.qq.com/s/BeF0LvQHawlQTo7daKtBZw");
            jsonObject.put("picurl","http://www.goddesses.net.cn/pic/user/938/4878223820852806.jpg");
            jsonArray.add(jsonObject);
        }
        JSONObject news = new JSONObject();
        news.put("articles",jsonArray);
        customMessage.setNews(news);
        String token = accessTokenService.getGlobalToken();
        String result = wxMessageService.customSend(customMessage,url,token);
        return AjaxResponse.success().setResult(result).setMsg("客户消息推送功成");
    }

}
