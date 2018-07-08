package com.yr.net.web.controller;

import com.yr.net.bean.AjaxResponse;
import com.yr.net.entity.Customer;
import com.yr.net.entity.Enroll;
import com.yr.net.model.JoinPartyReq;
import com.yr.net.service.UserService;
import com.yr.net.service.impl.EnrollService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.Date;

/**
 * All rights Reserved, Designed By SEGI
 * <pre>
 * Copyright:  Copyright(C) 2018
 * Company:    SEGI.
 * @Author: dengbp
 * @Date: 2018/7/3
 * </pre>
 * <p>
 *     参加约会、活动控制器
 * </p>
 */
@RestController
@RequestMapping("/party")
public class PartyController {
    private static final Logger logger = LoggerFactory.getLogger(PartyController.class);
    @Resource
    UserService userService;
    @Resource
    EnrollService enrollService;

    /**
     * 活动报名(报名支付成功后调)
     * @param joinPartyReq
     * @return 报名结果
     */
    @RequestMapping(method = RequestMethod.POST,path = "/join")
    public AjaxResponse register(@RequestBody JoinPartyReq joinPartyReq){
       if(StringUtils.isBlank(joinPartyReq.getOpenId()) && joinPartyReq.getUserId()==null){
           return new AjaxResponse(1,"用户信息不能为空");
       }
        if(StringUtils.isBlank(joinPartyReq.getPartyCode()) && joinPartyReq.getPartyId()==null){
            return new AjaxResponse(1,"活动信息不能为空");
        }
        logger.info("请求参数:openId[{}],partyId[{}]",joinPartyReq.getOpenId(),joinPartyReq.getPartyId());
        AjaxResponse ajaxResponse = new AjaxResponse(0,"报名成功");
        Customer customer = null;
        if(StringUtils.isNotBlank(joinPartyReq.getOpenId())){
            customer = userService.findCustomerByOpenId(joinPartyReq.getOpenId());
        }else if(joinPartyReq.getUserId()!=null){
            customer = userService.findCustomerById(joinPartyReq.getUserId());
        }
        if(customer == null){
            return new AjaxResponse(1,"用户还没注册");
        }
        Enroll enroll = new Enroll();
        this.copyProperty(customer,joinPartyReq,enroll);
        if (enrollService.saveOrUpdate(enroll)){
            ajaxResponse.setMsg(MessageFormat.format("您报名的活动编号是：{0},已报名成功,客服人员将会与您联系确认" , joinPartyReq.getPartyCode()));
        }else{
            ajaxResponse.setMsg(MessageFormat.format("编号是：{0}的活动,您已经报名，不能重复报名" , joinPartyReq.getPartyCode()));

        }
        return ajaxResponse;
    }


    /**
     * 复制对象属性
     * @param customer 用户信息
     * @param enroll 目标对象
     */
    private void copyProperty(Customer customer,JoinPartyReq joinPartyReq,Enroll enroll){
        enroll.setCreateTime(new Date());
        enroll.setIsAgreement(2);
        enroll.setPayAll(2);
        /**
         * 是否已支付 0：未支付；1:已支付
         */
        enroll.setPayDeposit(0);
        enroll.setPartyId(joinPartyReq.getPartyId());
        enroll.setPartyCode(joinPartyReq.getPartyCode());
        enroll.setPhone(customer.getPhone());
        enroll.setOpenId(joinPartyReq.getOpenId());
        enroll.setUserId(joinPartyReq.getUserId());
        enroll.setSex(customer.getSex());
        enroll.setWechatNickName(customer.getWeChartNickName());
        logger.info("enroll:{}",enroll);
    }
}
