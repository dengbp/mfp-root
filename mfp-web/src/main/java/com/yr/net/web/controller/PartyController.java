package com.yr.net.web.controller;

import com.yr.net.bean.AjaxResponse;
import com.yr.net.entity.Customer;
import com.yr.net.entity.Enroll;
import com.yr.net.entity.PartyTheme;
import com.yr.net.model.JoinPartyReq;
import com.yr.net.model.PartyApplyReq;
import com.yr.net.service.UserService;
import com.yr.net.service.impl.PartyService;
import com.yr.net.service.impl.PartyThemeService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

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
    PartyService partyService;
    @Resource
    PartyThemeService partyThemeService;

    /**
     * 活动，约会申请发布接口
     * @param partyApplyReq partyApplyReq
     * @return 响应结果
     * @throws Exception 业务异常
     */
    @PostMapping("/public")
    public AjaxResponse partyPublic(@RequestBody @Valid PartyApplyReq partyApplyReq) throws Exception {
        AjaxResponse ajaxResponse = new AjaxResponse(0,"发布成功");
        if (validatePublicInfo(partyApplyReq,ajaxResponse)){
            ajaxResponse.setCode(1);
            return ajaxResponse;
        }
        ajaxResponse.setCode(0);
        if (partyApplyReq.getPartyType().intValue()==1){
            partyApplyReq.setEnrollMax(2);
            partyApplyReq.setMaleMax(1);
            partyApplyReq.setFemaleMax(1);
            ajaxResponse.setMsg("约会申请/发布成功");
        }
        if (partyApplyReq.getPartyType().intValue()==2){
            ajaxResponse.setMsg("活动申请/发布成功");
        }
        partyService.save(partyApplyReq);
        return ajaxResponse;
    }

    private boolean validatePublicInfo(PartyApplyReq partyApplyReq,AjaxResponse ajaxResponse){
        boolean result = false;
        if (null == partyApplyReq.getPartyType()){
            ajaxResponse.setMsg("活动类型不能为空");
            return true;
        }
        if (StringUtils.isBlank(partyApplyReq.getName())){
            ajaxResponse.setMsg("约会、活动主题不能为空");
            return true;
        }
        if (null == partyApplyReq.getPartyTypeId()){
            ajaxResponse.setMsg("约会、活动方式id不能为空");
            return true;
        }
        if (StringUtils.isBlank(partyApplyReq.getPartyTypeName())){
            ajaxResponse.setMsg("活动方式名称不能为空");
            return true;
        }
        if (StringUtils.isBlank(partyApplyReq.getPartyContent())){
            ajaxResponse.setMsg("活动内容不能为空");
            return true;
        }
        /*活动类型校验*/
        if (partyApplyReq.getPartyType().intValue()==2){
            if(null == partyApplyReq.getEnrollMax()){
                ajaxResponse.setMsg("可报名人数不能为空");
                return true;
            }
            if(null == partyApplyReq.getFemaleMax()){
                ajaxResponse.setMsg("女报名人数不能为空");
                return true;
            }
            if (null == partyApplyReq.getMaleMax()){
                ajaxResponse.setMsg("男报名人数不能为空");
                return true;
            }
            if(StringUtils.isBlank(partyApplyReq.getBeginTimeStr())){
                ajaxResponse.setMsg("活动报名开始时间不能为空");
                return true;
            }
            if(StringUtils.isBlank(partyApplyReq.getEndTimeStr())){
                ajaxResponse.setMsg("活动报名结束时间不能为空");
                return true;
            }
            if(null == partyApplyReq.getEntryFee()){
                ajaxResponse.setMsg("报名费（单位分）不能为空");
                return true;
            }

        }
        if(StringUtils.isBlank(partyApplyReq.getConductAddr())){
            ajaxResponse.setMsg("活动地点不能为空");
            return true;
        }
        if(StringUtils.isBlank(partyApplyReq.getConductTimeStr())){
            ajaxResponse.setMsg("活动时间不能为空");
            return true;
        }
        if (null == partyApplyReq.getGoodsId()){
            ajaxResponse.setMsg("诚意金id不能为空");
            return true;
        }
        if (null == partyApplyReq.getPrice()){
            ajaxResponse.setMsg("诚意金金额（单位分）不能为空");
            return true;
        }
        if(StringUtils.isBlank(partyApplyReq.getPublisher())){
            ajaxResponse.setMsg("发布者名称不能为空");
            return true;
        }
        if(null == partyApplyReq.getPublisherId()){

            ajaxResponse.setMsg("发布者id不能为空");
            return true;
        }

        return result;
    }

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
        if (partyService.saveOrUpdate(enroll)){
            ajaxResponse.setMsg(MessageFormat.format("您报名的活动编号是：{0},已报名成功,客服人员将会与您联系确认" , joinPartyReq.getPartyCode()));
        }else{
            ajaxResponse.setMsg(MessageFormat.format("编号是：{0}的活动,您已经报名，不能重复报名" , joinPartyReq.getPartyCode()));

        }
        return ajaxResponse;
    }

    @GetMapping("/theme/query")
    public AjaxResponse queryTheme(@RequestParam("themeType") Integer themeType){
        List<PartyTheme> list = partyThemeService.findByType(themeType);
        AjaxResponse ajaxResponse = new AjaxResponse(0,"查询成功");
        ajaxResponse.setResult(list);
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
        enroll.setPartyId(joinPartyReq.getPartyId().intValue());
        enroll.setPartyCode(joinPartyReq.getPartyCode());
        enroll.setPhone(customer.getPhone());
        enroll.setOpenId(joinPartyReq.getOpenId());
        enroll.setUserId(joinPartyReq.getUserId().intValue());
        enroll.setSex(customer.getSex());
        enroll.setWechatNickName(customer.getWeChartNickName());
        logger.info("enroll:{}",enroll);
    }
}
