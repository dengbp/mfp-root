package com.yr.net.web.controller;

import com.yr.net.bean.AjaxResponse;
import com.yr.net.service.impl.InterestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * All rights Reserved, Designed By SEGI
 * <pre>
 * Copyright:  Copyright(C) 2018
 * Company:    SEGI.
 * Author:     dengbp
 * CreateDate: 2018/5/29
 * </pre>
 * <p>
 *     爱好控制器
 * </p>
 */
@Controller
public class InterestController {
    static Logger logger = LoggerFactory.getLogger(IndustryController.class);
    @Resource
    private InterestService interestService;

    @RequestMapping(method = RequestMethod.GET,path = "/interest/list")
    @ResponseBody
    public AjaxResponse interestList(){
        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.setCode(0);
        ajaxResponse.setResult(interestService.findAll());
        ajaxResponse.setMsg("成功");
        return ajaxResponse;
    }
}
