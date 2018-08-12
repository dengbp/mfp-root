package com.yr.net.bean;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * AJAX返回信息
 * All rights Reserved, Designed By HQYG
 * <pre>
 * Copyright:  Copyright(C) 2018
 * Company:    HQYG.
 * @Author:     dengbp
 * @CreateDate: 2018/5/4
 * </pre>
 * <p>
 * </p>
 */
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

    private static final int SUCCESS = 200;
    private static final int FAIL = 1;

    private AjaxResponse() {
    }

//    /**
//     * 构造器
//     * @param code 状态编码  200:成功；1:失败
//     * @param msg 针对状态附加描述
//     */
//    public AjaxResponse(int code, String msg) {
//        this.code = code;
//        this.msg = msg;
//    }
//
//    /**
//     *
//     * 构造器
//     * @param code 状态编码  0:成功；1:失败
//     * @param msg 针对状态附加描述
//     * @param result 结果数据对象返回
//     */
//    public AjaxResponse(int code, String msg, Object result) {
//        this.code = code;
//        this.msg = msg;
//        this.result = result;
//    }

    public static AjaxResponse success(){
        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.setCode(SUCCESS);
        return ajaxResponse;
    }

    public static AjaxResponse fail(int errorCode){
        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.setCode(errorCode);
        return ajaxResponse;
    }

    public static AjaxResponse fail(){
        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.setCode(FAIL);
        return ajaxResponse;
    }

    public int getCode() {
        return code;
    }

    private AjaxResponse setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public AjaxResponse setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getResult() {
        return result;
    }

    public AjaxResponse setResult(Object result) {
        this.result = result;
        return this;
    }
}
