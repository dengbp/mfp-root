package com.yr.net.web.controller;

import com.yr.net.bean.AjaxResponse;
import com.yr.net.service.CharacterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * All rights Reserved, Designed By SEGI
 * <pre>
 * Copyright:  Copyright(C) 2018
 * Company:    SEGI.
 * Author:     dengbp
 * CreateDate: 2018/5/29
 * </pre>
 * <p>
 *     性格控制器
 * </p>
 */
@Controller
public class CharacterController {
    static Logger log = LoggerFactory.getLogger(CharacterController.class);
    @Resource
    private CharacterService characterService;

    @RequestMapping(method = RequestMethod.GET,path = "/character/list")
    @ResponseBody
    public AjaxResponse characterList(){
        return AjaxResponse.success().setResult(characterService.findAll()).setMsg("成功");
    }

}
