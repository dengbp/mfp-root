package com.yr.net.http;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
/**
 * All rights Reserved, Designed By HQYG
 * <pre>
 * Copyright:  Copyright(C) 2018
 * Company:    HQYG.
 * Author:     dengbp
 * CreateDate: 2018/5/2
 * </pre>
 * <p>
 * </p>
 */
public class HttpUtils {
    private final static Logger logger = LoggerFactory.getLogger(HttpUtils.class);
//    private final String USER_AGENT = "Mozilla/5.0";

    /**
     *post方式发送请求
     * @param url url
     * @param data data
     * @return 请求结果
     */
    public static String sendByPost(String url,String data)
    {
        String msg = "";
        try{
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            //设置请求方式,请求超时信息
            conn.setRequestMethod("POST");
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            //设置运行输入,输出:
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestProperty("Charset", "utf-8");
            conn.setRequestProperty("Content-type","application/json");
            //Post方式不能缓存,需手动设置为false
            conn.setUseCaches(false);
            //获取输出流
            OutputStream out = conn.getOutputStream();
            out.write(data.getBytes());
            out.flush();
            if (conn.getResponseCode() == 200) {
                // 获取响应的输入流对象
                InputStream is = conn.getInputStream();
                // 创建字节输出流对象
                ByteArrayOutputStream message = new ByteArrayOutputStream();
                // 定义读取的长度
                int len = 0;
                // 定义缓冲区
                byte buffer[] = new byte[1024];
                // 按照缓冲区的大小，循环读取
                while ((len = is.read(buffer)) != -1) {
                    // 根据读取的长度写入到os对象中
                    message.write(buffer, 0, len);
                }
                // 释放资源
                is.close();
                message.close();
                // 返回字符串
                msg = new String(message.toByteArray());
                return msg;
            }
        }catch(Exception e){e.printStackTrace();}
        return msg;
    }

    /**
     * HTTP GET请求
     * @param url 请求url
     * @throws Exception
     */
    public static String sendGet(String url) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        //默认值我GET
        con.setRequestMethod("GET");
        //添加请求头
//        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        logger.info("\nSending 'GET' request to URL : " + url);
        logger.info("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        //打印结果
        logger.info(response.toString());
        return response.toString();
    }

    public static  void main(String[] args){
        //我们请求的数据:
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("account","N6130854");
        jsonObject.put("password","7y1Wjms5e");
        jsonObject.put("msg","【伊人网网络公司】您的验证码是：6352");
        jsonObject.put("phone","13530051353");

        String data = jsonObject.toJSONString();
        System.out.printf("请求数据：%s",data);
        String result = HttpUtils.sendByPost("http://smssh1.253.com/msg/send/json",data);
        System.out.printf("响应结果:%s",result);
    }
}
