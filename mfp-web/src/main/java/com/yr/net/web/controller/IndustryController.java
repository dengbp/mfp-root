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
        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.setCode(0);
        ajaxResponse.setMsg("成功");
        List<Industry> industryList = industryService1.getIndustry();
        ajaxResponse.setResult(industryList);
        return ajaxResponse;
    }

    @RequestMapping(method = RequestMethod.GET,path = "/jobTitle/list")
    @ResponseBody
    public AjaxResponse jobTitleList(Integer type){
        AjaxResponse ajaxResponse = new AjaxResponse();
        if (type == null){
            ajaxResponse.setCode(1);
            ajaxResponse.setMsg("行业类型为空");
            return ajaxResponse;
        }
        ajaxResponse.setCode(0);
        ajaxResponse.setMsg("成功");
        ajaxResponse.setResult(industryService1.getJobTitle(type));
        return ajaxResponse;
    }
}
