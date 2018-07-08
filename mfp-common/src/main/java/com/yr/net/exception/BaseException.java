package com.yr.net.exception;

import org.apache.commons.lang.exception.NestableRuntimeException;

import java.text.MessageFormat;

/**
 * All rights Reserved, Designed By HQYG
 * <pre>
 * Copyright:  Copyright(C) 2018
 * Company:    HQYG.
 * Author:     dengbp
 * CreateDate: 2018/5/11
 * </pre>
 * <p>
 *     异常基类
 * </p>
 */
public class BaseException extends RuntimeException{

    private String errorCode;
    private String msg;

    public BaseException() {
        super();
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String errorCode,String message) {
        super(MessageFormat.format("errorCode:{0},errorMessage:{1}", errorCode , message));
    }

    public BaseException(String errorCode,String message, Throwable cause) {
        super(MessageFormat.format("errorCode:{0},errorMessage:{1}", errorCode , message), cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
