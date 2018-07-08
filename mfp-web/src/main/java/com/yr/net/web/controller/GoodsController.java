package com.yr.net.web.controller;

import com.yr.net.bean.AjaxResponse;
import com.yr.net.service.impl.CharacterService;
import com.yr.net.service.impl.GoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * All rights Reserved, Designed By SEGI
 * <pre>
 * Copyright:  Copyright(C) 2018
 * Company:    SEGI.
 * @Author:     dengbp
 * @Date: 2018/5/29
 * </pre>
 * <p>
 *     性格控制器
 * </p>
 */
@Controller
public class GoodsController {
    static Logger log = LoggerFactory.getLogger(GoodsController.class);
    @Resource
    private GoodsService goodsService;

    @RequestMapping(method = RequestMethod.GET,path = "/goods/list")
    @ResponseBody
    public AjaxResponse goodsList(@RequestParam("typeId") Long typeId){
        log.info("call goodsList method...");
        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.setCode(0);
        ajaxResponse.setResult(goodsService.findByTypeId(typeId));
        ajaxResponse.setMsg("成功");
        return ajaxResponse;
    }

}
