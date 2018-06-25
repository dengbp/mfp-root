package com.yr.net.bean;

import lombok.Getter;
import lombok.Setter;

/**
 * AJAX返回信息
 * All rights Reserved, Designed By HQYG
 * <pre>
 * Copyright:  Copyright(C) 2018
 * Company:    HQYG.
 * Author:     dengbp
 * CreateDate: 2018/5/4
 * </pre>
 * <p>
 * </p>
 */
@Setter
@Getter
public class AjaxResponse {

    /**
     * 状态编码 0:成功；1:失败
     */
    private int code;

    /**
     * 状态对应的描述
     */
    private String msg;

    /**
     * 需要返回的信息
     */
    private Object result;


    public AjaxResponse() {
    }

    /**
     * 构造器
     * @param code 状态编码  0:成功；1:失败
     * @param msg 针对状态附加描述
     */
    public AjaxResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     *
     * 构造器
     * @param code 状态编码  0:成功；1:失败
     * @param msg 针对状态附加描述
     * @param result 结果数据对象返回
     */
    public AjaxResponse(int code, String msg, Object result) {
        this.code = code;
        this.msg = msg;
        this.result = result;
    }
}
