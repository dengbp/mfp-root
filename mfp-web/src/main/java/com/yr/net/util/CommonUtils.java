package com.yr.net.util;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CommonUtils {
    /**
     * 生成订单id
     * @return
     */
    public static String genOrderId(Integer custId){
        return getCurrentDate("yyyyMMddHHmmss")
                + substring(String.valueOf(custId), 0, 4)
                + RandomStringUtils.randomNumeric(4);
    }

    public static String getCurrentDate(String format) {

        DateFormat f = new SimpleDateFormat(format);

        return f.format(Calendar.getInstance().getTime());

    }

    /**
     * 截取 字符串，不够以0 补充
     *
     * @param str
     *            字符串
     * @param start
     *            截取开始位置(包含)
     * @param end
     *            截取结束位置(不包含)
     * @return
     */
    private static String substring(String str, int start, int end) {
        int len = 0;
        if (StringUtils.isNotBlank(str)) {
            len = str.length();
        }

        if (len < end) {// 长度不够，补充 0
            return append(str, len, end).substring(start, end);
        } else {
            return str.substring(start, end);
        }

    }

    private static String append(String str, int start, int end) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < end; i++) {
            sb.append("0");
        }
        sb.append(str);
        return sb.toString();
    }
    /**
     * 截取最后字符规定长度的字符串，不足以0补充
     * @param str 字符串
     * @param subLen 截取长度
     * @return
     */
    private static String substringLast(String str, int subLen){
        int leng = 0;
        if(StringUtils.isNotBlank(str)){
            leng = str.length();
        }
        if(leng < subLen){
            return append(str, leng, subLen);
        }else{
            return str.substring(leng - subLen);
        }
    }

    public static void main(String[] args) {
        System.out.println(genOrderId(907));
    }
}
