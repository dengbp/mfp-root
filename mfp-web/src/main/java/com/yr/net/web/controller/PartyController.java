package com.yr.net.web.controller;

import com.yr.net.bean.AjaxResponse;
import com.yr.net.bean.UsersBean;
import com.yr.net.entity.Customer;
import com.yr.net.entity.Enroll;
import com.yr.net.entity.PartyApply;
import com.yr.net.entity.PartyTheme;
import com.yr.net.model.JoinPartyReq;
import com.yr.net.model.PartyApplyReq;
import com.yr.net.service.*;
import com.yr.net.util.RegexUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
@Slf4j
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
    @Resource
    PartyRefuseService partyRefuseService;
    @Resource
    WxMessageService wxMessageService;
    @Value("${party.info.url}")
    private String url;

    @GetMapping("/getRefuse")
    public AjaxResponse getRefuse(@RequestParam(value = "typeId") Integer typeId){
        return AjaxResponse.success().setResult( partyRefuseService.queryByType(typeId));
    }

    /**
     * 活动，约会申请发布、修改接口
     * @param partyApplyReq partyApplyReq
     * @return 响应结果
     * @throws Exception 业务异常
     */
    @PostMapping("/public")
    public AjaxResponse partyPublic(@RequestBody @Valid PartyApplyReq partyApplyReq) throws Exception {
        AjaxResponse ajaxResponse = AjaxResponse.fail();
        if (validatePublicInfo(partyApplyReq,ajaxResponse)){
            return ajaxResponse;
        }
        if (partyApplyReq.getPartyType().intValue()==1){
            partyApplyReq.setEnrollMax(2);
            partyApplyReq.setMaleMax(1);
            partyApplyReq.setFemaleMax(1);
            ajaxResponse = AjaxResponse.success().setMsg("约会申请/发布成功");
        }
        if (partyApplyReq.getPartyType().intValue()==2){
            ajaxResponse = AjaxResponse.success().setMsg("活动申请/发布成功");
        }

        Customer usersBean = userService.findCustomerById(partyApplyReq.getPublisherId());
        partyApplyReq.setPublisher(usersBean.getUserName());
        Long applyId = partyApplyReq.getId();
        if(null != applyId){
            List<PartyApply> list = partyService.queryByType(applyId, partyApplyReq.getPartyType());
            partyApplyReq.setCreateTime(list.get(0).getCreateTime());
        }

        PartyApply partyApply = partyService.save(partyApplyReq);

        //开始约会推送
        Long partyStatus = partyApply.getStatus();
        if(null != partyStatus) {
            Map<String,String> mapData = new HashMap<>();
            mapData.put("partyTime", DateFormatUtils.format(partyApply.getConductTime(), "yyyy-MM-dd HH:mm"));
            mapData.put("reason",partyApply.getReason());
            mapData.put("remarks",partyApply.getRemarks());

            if(partyStatus == 0){
                //约会申请提交(推送给被约人)
                //获取被约者用户数据
                String publisherName = usersBean.getUserName();
                usersBean = userService.findCustomerById(partyApply.getSecondId());
                mapData.put("openid", usersBean.getOpenId());
                mapData.put("userName", publisherName);
                mapData.put("first", "您好，您有一个邀约申请");
                mapData.put("link", url.concat("partyContract?applyId=").concat(partyApply.getId().toString()));
            } if (partyStatus == 4) {
                //同意约会(推送给申请人)
                mapData.put("openid", usersBean.getOpenId());
                mapData.put("userName", usersBean.getUserName());
                mapData.put("first", "您好，对方已同意您的邀约申请");
                mapData.put("link", url.concat("partyReview?applyId=").concat(partyApply.getId().toString()));
            } else if (partyStatus == 5) {
                //拒绝约会(推送给申请人)
                mapData.put("openid", usersBean.getOpenId());
                mapData.put("userName", usersBean.getUserName());
                mapData.put("first", "您好，对方已拒绝您的邀约申请");
                mapData.put("link", url.concat("partyReview?applyId=").concat(partyApply.getId().toString()));
            } else if (partyStatus == 1) {
                //开始约会(推送给被约人)
                String publisherName = usersBean.getUserName();
                usersBean = userService.findCustomerById(partyApply.getSecondId());
                mapData.put("openid", usersBean.getOpenId());
                mapData.put("userName", publisherName);
                mapData.put("first", "对方已同意您的邀约，请打扮得漂漂亮亮的准备赴约吧");
                mapData.put("link", url.concat("partyReview?applyId=").concat(partyApply.getId().toString()));
            } else if (partyStatus == 6) {
                //毁约(推送给被约人)
                String publisherName = usersBean.getUserName();
                usersBean = userService.findCustomerById(partyApply.getSecondId());
                mapData.put("openid", usersBean.getOpenId());
                mapData.put("userName", publisherName);
                mapData.put("first", "您好，发起人已取消本次约会");
                mapData.put("link", url.concat("partyReview?applyId=").concat(partyApply.getId().toString()));
            }

            wxMessageService.sendPartyMsg(mapData);
            return ajaxResponse;
        }else{
            ajaxResponse = AjaxResponse.success().setMsg("活动申请/发布失败");
        }
        return ajaxResponse;
    }

    /**
     * 活动、约会查询接口
     * @param partyType 活动类型：1：约会；2：活动
     * @param id id
     * @return 查结果
     */
    @GetMapping("/query")
    public AjaxResponse query(@RequestParam(value = "id")Long id,@RequestParam(value = "partyType") Integer partyType){
        return AjaxResponse.success().setResult(partyService.queryByType(id,partyType)).setMsg("查询成功");
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
        if(1==partyApplyReq.getPartyType().intValue()){
            if (partyApplyReq.getSecondId()==null){
                if(null == partyApplyReq.getEnrollMax()){
                    ajaxResponse.setMsg("被约人id不能为空");
                    return true;
                }
            }
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

        if(StringUtils.isBlank(partyApplyReq.getReason())){
            ajaxResponse.setMsg("约TA理由不能为空");
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
    public AjaxResponse join(@RequestBody JoinPartyReq joinPartyReq){
       if(StringUtils.isBlank(joinPartyReq.getOpenId()) && joinPartyReq.getUserId()==null){
           return AjaxResponse.fail().setMsg("用户信息不能为空");
       }
        if(StringUtils.isBlank(joinPartyReq.getPartyCode()) && joinPartyReq.getPartyId()==null){
            return AjaxResponse.fail().setMsg("活动信息不能为空");
        }
        logger.info("请求参数:openId[{}],partyId[{}]",joinPartyReq.getOpenId(),joinPartyReq.getPartyId());
        Customer customer = null;
        if(StringUtils.isNotBlank(joinPartyReq.getOpenId())){
            customer = userService.findCustomerByOpenId(joinPartyReq.getOpenId());
        }else if(joinPartyReq.getUserId()!=null){
            customer = userService.findCustomerById(joinPartyReq.getUserId());
        }
        if(customer == null){
            return AjaxResponse.fail().setMsg("用户还没注册");
        }
        Enroll enroll = new Enroll();
        this.copyProperty(customer,joinPartyReq,enroll);
        if (partyService.saveOrUpdate(enroll)){
            return AjaxResponse.success().setMsg(MessageFormat.format("您报名的活动编号是：{0},已报名成功,客服人员将会与您联系确认" , joinPartyReq.getPartyCode()));
        }else{
            return AjaxResponse.success().setMsg(MessageFormat.format("编号是：{0}的活动,您已经报名，不能重复报名" , joinPartyReq.getPartyCode()));

        }
    }













    /**
     * 活动报名(临时使用)
     * @param request request
     * @param phone phone
     * @param code code
     * @param codeTime codeTime
     * @return 响应结果
     */
    @RequestMapping(method = RequestMethod.POST,path = "/join2")
    @ResponseBody
    public AjaxResponse join2(HttpServletRequest request, String phone, String code, String codeTime, String partyId){
        logger.info("请求参数:phone[{}],code[{}],codeTime[{}],partyId[{}]",phone,code,codeTime,partyId);
        String[] preCode = codeTime.split("_");
        if (preCode.length != 3){
            return AjaxResponse.fail().setMsg("先获取手机验证码");
        }
        if(!RegexUtils.checkMobile(phone)){
            return AjaxResponse.fail().setMsg("手机号码格式不正确");
        }
        if (!StringUtils.equals(phone,preCode[2])){
            return AjaxResponse.fail().setMsg("手机号码校验不通过");
        }
        String id = (String) request.getSession().getAttribute("code");
        long current = System.currentTimeMillis();
        if(id.equals(code) && (current - new Long(preCode[1]).longValue())<1000 * 60 * 2){
            Customer customer = new Customer();
            customer.setPhone(phone);
            customer = userService.saveOrUpdate(customer);
            Enroll enroll = new Enroll();
            this.copyProperty2(customer,enroll);
            enroll.setPartyCode(partyId);
            enroll.setPhone(phone);
            if (partyService.saveOrUpdate(enroll)){
                return AjaxResponse.success().setMsg(MessageFormat.format("您报名的活动编号是：{0},已报名成功,稍后客服人员将会与您联系确认" , partyId));
            }else{
                return AjaxResponse.success().setMsg(MessageFormat.format("编号是：{0}的活动,您已经报名，不能重复报名" , partyId));

            }
        }
        return AjaxResponse.fail().setMsg("验证码验证失败,请重新获取验证码");
    }


    /**
     * 复制对象属性(临时使用)
     * @param customer 用户信息
     * @param enroll 目标对象
     */
    private void copyProperty2(Customer customer,Enroll enroll){
        enroll.setCreateTime(new Date());
        enroll.setIsAgreement(2);
        enroll.setPayAll(2);
        enroll.setPayDeposit(2);
        logger.info("customer:{}",customer);
        if(customer.getSex() != null){
            enroll.setSex(customer.getSex());
        }
        enroll.setWechatNickName(customer.getWeChartNickName());
    }








    @GetMapping("/theme/query")
    public AjaxResponse queryTheme(@RequestParam("themeType") Integer themeType){
        List<PartyTheme> list = partyThemeService.findByType(themeType);
        return AjaxResponse.success().setResult(list).setMsg("查询成功");
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
