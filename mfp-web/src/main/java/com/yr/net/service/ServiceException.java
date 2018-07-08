package com.yr.net.service;

import com.yr.net.exception.BaseException;

/**
 * All rights Reserved, Designed By HQYG
 * <pre>
 * Copyright:  Copyright(C) 2018
 * Company:    HQYG.
 * Author:     dengbp
 * CreateDate: 2018/5/11
 * </pre>
 * <p>
 * </p>
 */
public class ServiceException extends BaseException{
    public ServiceException(String errorCode, String message) {
        super(errorCode, message);
    }

    public ServiceException(String message) {
        super(message);
    }
}
