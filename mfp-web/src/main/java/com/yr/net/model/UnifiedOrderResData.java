package com.yr.net.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * APP支付统一下单响应类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UnifiedOrderResData {
    //协议层
    private String return_code = "";
    private String return_msg = "";

    //协议返回的具体数据（以下字段在return_code 为SUCCESS 的时候有返回）
    private String appid = "";
    private String mch_id = "";
    private String device_info = "";
    private String nonce_str = "";
    private String sign = "";
    private String result_code = "";
    private String err_code = "";
    private String err_code_des = "";


    //业务返回的具体数据（以下字段在return_code 和result_code 都为SUCCESS 的时候有返回）
    private String trade_type = "";
    private String prepay_id = "";
    private String code_url = "";
}
