package com.yr.net.web.controller;

import com.yr.net.bean.AjaxResponse;
import com.yr.net.entity.Industry;
import com.yr.net.service.IndustryService1;
import com.yr.net.service.impl.IndustryService1Impl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * All rights Reserved, Designed By SEGI
 * <pre>
 * Copyright:  Copyright(C) 2018
 * Company:    SEGI.
 * @Author:     dengbp
 * @Date: 2018/5/29
 * </pre>
 * <p>
 *    行业职务控制器
 * </p>
 */
@Controller
public class IndustryController {
    static Logger logger = LoggerFactory.getLogger(IndustryController.class);
    @Resource
    private IndustryService1 industryService1;

    @RequestMapping(method = RequestMethod.GET,path = "/industry/list")
    @ResponseBody
    public AjaxResponse industryList(){
        List<Industry> industryList = industryService1.getIndustry();
        return AjaxResponse.success().setMsg("成功").setResult(industryList);
    }

    @RequestMapping(method = RequestMethod.GET,path = "/jobTitle/list")
    @ResponseBody
    public AjaxResponse jobTitleList(Integer type){
        if (type == null){
            return AjaxResponse.fail().setMsg("行业类型为空");
        }
        return AjaxResponse.success().setResult(industryService1.getJobTitle(type)).setMsg("成功");
    }
}
