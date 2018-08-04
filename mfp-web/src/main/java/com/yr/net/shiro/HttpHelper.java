package com.yr.net.shiro;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * All rights Reserved, Designed By SEGI
 * <pre>
 * Copyright:  Copyright(C) 2018
 * Company:    SEGI.
 * @Author: dengbp
 * @Date: 2018/8/4
 * </pre>
 * <p>
 * </p>
 */
public class HttpHelper {
    private static Logger logger = LoggerFactory.getLogger(HttpHelper.class);

    /**
     * 解析post请求body数据
     * @param request request
     * @return body串
     */
    public static String getBodyString(ServletRequest request){
        InputStream inputStream = null;
        BufferedReader streamReader = null;
        StringBuilder responseStrBuilder = new StringBuilder();
        try {
            inputStream = request.getInputStream();
            streamReader = new BufferedReader( new InputStreamReader(request.getInputStream(), "UTF-8"));
            String inputStr;
            while((inputStr = streamReader.readLine()) != null){
                responseStrBuilder.append(inputStr);
            }
        }catch (IOException e) {
        logger.error("getBodyString出现问题！",e);
    }
    finally {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (streamReader != null) {
            try {
                streamReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    return responseStrBuilder.toString();
    }
}
