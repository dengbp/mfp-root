package com.yr.net.web.controller;

import com.yr.net.bean.AjaxResponse;
import com.yr.net.entity.Customer;
import com.yr.net.entity.TopLine;
import com.yr.net.service.UserService;
import com.yr.net.service.impl.TopLineService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
 *     申请上伊人头条控制器
 * </p>
 */
@RestController
@RequestMapping("/topLine")
public class TopLineController {
    private static final Logger logger = LoggerFactory.getLogger(TopLineController.class);
    @Resource
    UserService userService;
    @Resource
    TopLineService topLineService;

    @RequestMapping(method = RequestMethod.POST,path = "/apply")
    public AjaxResponse apply(@RequestBody TopLine topLine){
        logger.info("begin call apply method...");
        if(StringUtils.isBlank(topLine.getOpenId()) && topLine.getUserId()==null){
            return AjaxResponse.fail().setMsg("用户id不能为空");
        }
        if(StringUtils.isBlank(topLine.getOpenId())){
            return AjaxResponse.fail().setMsg("openId不能为空");
        }
        Customer customer = userService.searchByOpenId(topLine.getOpenId());
        this.copyProperty(customer,topLine);
        topLineService.save(topLine);
        return AjaxResponse.success().setMsg("报名成功");
    }

    @RequestMapping(method = RequestMethod.GET,path = "/signed")
    public AjaxResponse signed(Long userId,String openId){
        if(userId==null && StringUtils.isBlank(openId)){
            return AjaxResponse.fail().setMsg("openId和userId不能为空");
        }
        return AjaxResponse.success().setResult(topLineService.signed(userId,openId)).setMsg("请求成功");
    }

    /**
     * 属性赋值
     * @param customer customer
     * @param topLine topLine
     */
    private void copyProperty(Customer customer,TopLine topLine){
        topLine.setCreateTime(new Date());
        topLine.setUserPhone(customer.getPhone());
        topLine.setUserId(customer.getId());
        topLine.setSex(customer.getSex());
    }
}
