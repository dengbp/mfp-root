package com.yr.net.util;

/**
 * @author dengbp
 * @ClassName BinaryUtil
 * @Description 二进制转16进制
 * @date 2018/12/24 下午7:36
 */
public class BinaryUtil {
    public static String binToHex(byte[] data) {
        StringBuilder result = new StringBuilder();
        int n = 0;
        for (byte b : data) {
            if (n % 16 == 0) {
                result.append(String.format("%05x: ", n));
            }
            result.append(String.format("%02x ", b));
            n++;
            if((n % 16 == 0)){
                 result.append("\n");
            }
        }
        result.append("\n");
        return result.toString();
    }

}
