package com.yr.net.web.controller;

import com.yr.net.bean.AjaxResponse;
import com.yr.net.entity.OrderEntity;
import com.yr.net.model.OrderReq;
import com.yr.net.service.OrderService;
import com.yr.net.service.WxMessageService;
import com.yr.net.service.WxPayService;
import com.yr.net.service.AccessTokenService;
import com.yr.net.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import weixin.popular.bean.paymch.MchBaseResult;
import weixin.popular.bean.paymch.MchPayNotify;
import weixin.popular.support.ExpireKey;
import weixin.popular.support.expirekey.DefaultExpireKey;
import weixin.popular.util.SignatureUtil;
import weixin.popular.util.StreamUtils;
import weixin.popular.util.XMLConverUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.Map;

@Controller
@RequestMapping("/wx/pay")
public class WxPayController {
    private static final Logger logger = LoggerFactory.getLogger(WxPayController.class);

    private final WxPayService wxPayService;
    private final OrderService orderService;
    private final WxMessageService wxMessageService;

    @Autowired
    public WxPayController(WxPayService wxPayService, OrderService orderService, AccessTokenService accessTokenService, WxMessageService wxMessageService) {
        this.wxPayService = wxPayService;
        this.orderService = orderService;
        this.wxMessageService = wxMessageService;
    }

    //重复通知过滤
    private static ExpireKey expireKey = new DefaultExpireKey();

    /**
     * 统一下单
     * @param orderReq
     * @param request
     * @return
     */
    @RequestMapping("/unifiedorder")
    @ResponseBody
    public AjaxResponse unifiedOrder(@RequestBody OrderReq orderReq, HttpServletRequest request){
        orderReq.setRemoteIp(request.getRemoteAddr());
        //生成本地商品订单
        OrderEntity orderEntity = orderService.createOrder(orderReq);
        //微信统一下单接口
        orderReq.setOrderId(orderEntity.getOrderId());
        String json = wxPayService.unifiedorder(orderReq);
        return AjaxResponse.success().setResult(json).setMsg("下单成功");
    }



    @RequestMapping("/notify")
    public void notify(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
        logger.info("微信支付回调");
        //获取请求数据
        String xmlData = StreamUtils.copyToString(request.getInputStream(), Charset.forName("utf-8"));
        //将XML转为MAP,确保所有字段都参与签名验证
        Map<String,String> mapData = XMLConverUtil.convertToMap(xmlData);
        //转换数据对象
        MchPayNotify payNotify = XMLConverUtil.convertToObject(MchPayNotify.class,xmlData);
        //已处理 去重
        if(expireKey.exists(payNotify.getTransaction_id())){
            MchBaseResult baseResult = new MchBaseResult();
            baseResult.setReturn_code("SUCCESS");
            baseResult.setReturn_msg("OK");
            response.getOutputStream().write(XMLConverUtil.convertToXML(baseResult).getBytes());
            return;
        }
        //签名验证
        if(SignatureUtil.validateSign(mapData,Constants.MCH_KEY)){
            //@since 2.8.5
            payNotify.buildDynamicField(mapData);

            expireKey.add(payNotify.getTransaction_id());
            MchBaseResult baseResult = new MchBaseResult();
            baseResult.setReturn_code("SUCCESS");
            baseResult.setReturn_msg("OK");

            String orderId = mapData.get("out_trade_no");
            //更新订单支付状态
            orderService.updateState(orderId);
            //发送模板消息
            wxMessageService.sendBuySuccessMsg(mapData);
            response.getOutputStream().write(XMLConverUtil.convertToXML(baseResult).getBytes());
        }else{
            MchBaseResult baseResult = new MchBaseResult();
            baseResult.setReturn_code("FAIL");
            baseResult.setReturn_msg("ERROR");
            response.getOutputStream().write(XMLConverUtil.convertToXML(baseResult).getBytes());
        }
    }

}
