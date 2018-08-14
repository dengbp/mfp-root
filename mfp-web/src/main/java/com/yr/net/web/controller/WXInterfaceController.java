package com.yr.net.web.controller;

import com.yr.net.bean.AjaxResponse;
import com.yr.net.service.AccessTokenService;
import com.yr.net.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * All rights Reserved, Designed By SEGI
 * <pre>
 * Copyright:  Copyright(C) 2018
 * Company:    SEGI.
 * Author:     dengbp
 * CreateDate: 2018/6/20
 * </pre>
 * <p>
 *     微信接口控制器
 * </p>
 */
@Controller
public class WXInterfaceController {
    private static final Logger logger = LoggerFactory.getLogger(WXInterfaceController.class);

    private String token = Constants.TOKEN;
    private String encodingAESKey =Constants.CORPSECRET;
    private String appID = Constants.APPID;
    @Resource
    private AccessTokenService accessTokenService;
    @Value("${wx.web.token.url}")
    private String tempUrl;
    @Value("${wx.user.info.url}")
    private String tempUserInfoUrl;

    @RequestMapping(value = { "/wx/receiveMessage" }, method = RequestMethod.GET)
    public void coreJoinGet(HttpServletRequest request,
                            HttpServletResponse response) throws IOException {
        // 微信加密签名(企业号为msg_signature)
//      String msg_signature = request.getParameter("msg_signature");
//与企业号不同1
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");
        logger.info("signature:{}",signature);
        logger.info("timestamp:{}",timestamp);
        logger.info("nonce:{}",nonce);
        logger.info("echostr:{}",echostr);

       /* PrintWriter out = response.getWriter();
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        String result = null;
        try {
            WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(token, encodingAESKey,
                    appID);
            result = wxcpt.VerifyURL(signature, timestamp, nonce, echostr);
            System.out.println("result:"+result);
        } catch (AesException e) {
            e.printStackTrace();
        }
        if (result == null) {
            result = token;
        }
        out.print(result);
        out.close();
        out = null;*/
    }

    /**
     * 全局接口token
     * @return token
     */
    @RequestMapping(method = RequestMethod.GET,path = "/wx/global/token")
    @ResponseBody
    public AjaxResponse getGlobalToken() {
        logger.info("call getGlobalToken method...");
        String token =  accessTokenService.getGlobalToken();
        return AjaxResponse.success().setResult(token).setMsg("获取成功");
    }

    /**
     * 网页token接口
     * @param code code
     * @return token
     */
    @RequestMapping(method = RequestMethod.GET,path = "/wx/web/token")
    @ResponseBody
    public AjaxResponse getWebToken(String code) {
        logger.info("call getWebToken method...");
        String url = tempUrl.replace("CODE",code);
        logger.info("url:{}",url);
        String token = accessTokenService.getWebToken(url);
        return AjaxResponse.success().setResult(token).setMsg("获取成功");
    }

    @RequestMapping(method = RequestMethod.GET,path = "/wx/web/userInfo")
    @ResponseBody
    public AjaxResponse getUserInfo(String openId,String token) {
        logger.info("call getUserInfo method...");
        String url = tempUserInfoUrl.replace("ACCESS_TOKEN",token).replace("OPENID",openId);
        logger.info("url:{}",url);
        String userInfo = accessTokenService.getUserInfo(url);
        return AjaxResponse.success().setResult(userInfo).setMsg("获取成功");
    }

}
