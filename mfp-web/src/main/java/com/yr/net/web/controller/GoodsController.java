package com.yr.net.web.controller;

import com.yr.net.bean.AjaxResponse;
import com.yr.net.service.impl.CharacterService;
import com.yr.net.service.impl.GoodsService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Arrays;
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
        log.info("call goodsList method typeId[{}]...",typeId);
        if (typeId==null){
            return AjaxResponse.fail().setMsg("typeId is blank");
        }
        return AjaxResponse.success().setMsg("成功").setResult(goodsService.findByTypeId(typeId));
    }

    @RequestMapping(method = RequestMethod.GET,path = "/goods/list/typeIds")
    @ResponseBody
    public AjaxResponse goodsList(@RequestParam("typeIds") String typeIds){
        log.info("call goodsList method  typeIds[{}]...",typeIds);
        if (StringUtils.isBlank(typeIds)){
            return AjaxResponse.fail().setMsg("typeIds is blank");
        }
        String[] strings = typeIds.split(",");
        Long[] longs = new Long[strings.length];
        List<String> list = Arrays.asList(strings);
        list.stream().forEach(e -> {
            int index = list.indexOf(e);
            longs[index] = Long.valueOf(strings[index]);
        });
       return AjaxResponse.success().setMsg("成功").setResult(goodsService.findByIdTypeIds(longs));
    }

}
