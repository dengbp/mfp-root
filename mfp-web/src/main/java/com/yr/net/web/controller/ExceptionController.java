package com.yr.net.web.controller;

import com.yr.net.bean.AjaxResponse;
import com.yr.net.exception.UnauthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
/**
 * All rights Reserved, Designed By SEGI
 * <pre>
 * Copyright:  Copyright(C) 2018
 * Company:    SEGI.
 * @Author:     dengbp
 * @Date: 2018/7/27
 * </pre>
 * <p>
 *     异常处理
 * </p>
 */
@Slf4j
@RestControllerAdvice
public class ExceptionController {

    /**
     * 捕捉shiro的异常
     * @param e e
     * @return
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ShiroException.class)
    public AjaxResponse handle401(ShiroException e) {
        return AjaxResponse.fail(401).setMsg(e.getMessage());
    }

    /**
     * 捕捉UnauthorizedException
     * @return
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public AjaxResponse handle401() {
        return AjaxResponse.fail(401).setMsg("Unauthorized");
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }

    /**
     * 捕捉其他所有异常
     * @param request request
     * @param ex ex
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public AjaxResponse globalException(HttpServletRequest request, Throwable ex) {
        log.error(ex.getMessage());
        return AjaxResponse.fail(getStatus(request).value()).setMsg(ex.getMessage());
    }
    /**
     * 捕捉其他所有异常
     * @param request request
     * @param ex ex
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public AjaxResponse globalRuntimeException(HttpServletRequest request, Throwable ex) {
        log.error(ex.getMessage());
        return AjaxResponse.fail(getStatus(request).value()).setMsg(ex.getMessage());
    }
}

