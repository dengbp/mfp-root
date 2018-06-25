package com.yr.net.util;

import org.apache.commons.lang.StringUtils;

import java.util.regex.Pattern;

/**
 * All rights Reserved, Designed By SEGI
 * <pre>
 * Copyright:  Copyright(C) 2018
 * Company:    SEGI.
 * Author:     dengbp
 * CreateDate: 2018/5/29
 * </pre>
 * <p>
 *     正则校验工具
 * </p>
 */

public class RegexUtils {

    /******************** 正则相关常量 ********************/

    public static final String REGEX_OUTER = "<a.*?/a>";

    public static final String REGET_INNER = "<a .*href=\"(\\S+)\".*>(.+)</a>";

    /**
     * Good characters for Internationalized Resource Identifiers (IRI).
     * This comprises most common used Unicode characters allowed in IRI
     * as detailed in RFC 3987.
     * Specifically, those two byte Unicode characters are not included.
     */
    public static final String GOOD_IRI_CHAR =
            "a-zA-Z0-9";

    public static final String IP_ADDRESS =
            "((25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9])\\.(25[0-5]|2[0-4]"
                    + "[0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1]"
                    + "[0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}"
                    + "|[1-9][0-9]|[0-9]))";

    /**
     * RFC 1035 Section 2.3.4 limits the labels to a maximum 63 octets.
     */
    public static final String IRI
            = "[" + GOOD_IRI_CHAR + "]([" + GOOD_IRI_CHAR + "\\-]{0,61}[" + GOOD_IRI_CHAR
            + "]){0,1}";

    public static final String GOOD_GTLD_CHAR =
            "a-zA-Z";

    public static final String GTLD = "[" + GOOD_GTLD_CHAR + "]{2,63}";

    public static final String HOST_NAME = "(" + IRI + "\\.)+" + GTLD;

    public static final String DOMAIN_NAME
            = "(" + HOST_NAME + "|" + IP_ADDRESS + ")";

    /**
     * Regular expression pattern to match most part of RFC 3987
     * Internationalized URLs, aka IRIs. Commonly used Unicode characters are
     * added.
     */
    public static final String WEB_URL =
            "((?:(http|https|Http|Https):\\/\\/(?:(?:[a-zA-Z0-9\\$\\-\\_\\.\\+\\!\\*\\'\\(\\)"
                    + "\\,\\;\\?\\&\\=]|(?:\\%[a-fA-F0-9]{2})){1,64}(?:\\:(?:[a-zA-Z0-9\\$\\-\\_"
                    + "\\.\\+\\!\\*\\'\\(\\)\\,\\;\\?\\&\\=]|(?:\\%[a-fA-F0-9]{2})){1,25})?\\@)?)?"
                    + "(?:" + DOMAIN_NAME + ")"
                    + "(?:\\:\\d{1,5})?)" // plus option port number
                    + "(\\/(?:(?:[" + GOOD_IRI_CHAR + "\\;\\/\\?\\:\\@\\&\\=\\#\\~"
                    // plus option query params
                    + "\\-\\.\\+\\!\\*\\'\\(\\)\\,\\_])|(?:\\%[a-fA-F0-9]{2}))*)?";

    /**
     * 正则：手机号（简单）
     */
    public static final String REGEX_MOBILE_SIMPLE = "^[1]\\d{10}$";
    /**
     * 正则：手机号（精确）
     * <p>移动：134(0-8)、135、136、137、138、139、147、150、151、152、157、158、159、178、182、183、184、187、188</p>
     * <p>联通：130、131、132、145、155、156、175、176、185、186</p>
     * <p>电信：133、153、173、177、180、181、189</p>
     * <p>全球星：1349</p>
     * <p>虚拟运营商：170</p>
     */
    public static final String REGEX_MOBILE_EXACT  = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|(147))\\d{8}$";
    /**
     * 正则：电话号码
     */
    public static final String REGEX_TEL           = "^0\\d{2,3}[- ]?\\d{7,8}";


    /**
     * 正则：身份证号码
     */
    public static final String REGEX_ID_CARD    = "^(\\d{15}$|^\\d{17}[0-9A-Z])$";
    /**
     * 正则：邮箱
     */
    public static final String REGEX_EMAIL         = "\\w+@\\w+\\.[a-z]+(\\.[a-z]+)?";
    /**
     * 正则：URL
     */
    public static final String REGEX_URL           = "[a-zA-z]+://[^\\s]*";
    /**
     * 正则：用户名，取值范围为a-z,A-Z,0-9,"_",汉字，不能以"_"结尾,用户名必须是6-20位
     */
    public static final String REGEX_USERNAME      = "^[\\w\\u4e00-\\u9fa5]{6,20}(?<!_)$";
    /**
     * 正则：yyyy-MM-dd格式的日期校验，已考虑平闰年
     */
    public static final String REGEX_DATE          = "^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$";
    /**
     * 正则：IP地址
     */
    public static final String REGEX_IP            = "((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)";

    /**
     * 正则：双字节字符(包括汉字在内)
     */
    public static final String REGEX_DOUBLE_BYTE_CHAR     = "[^\\x00-\\xff]";
    /**
     * 正则：空白行
     */
    public static final String REGEX_BLANK_LINE           = "\\n\\s*\\r";
    /**
     * 正则：QQ号
     */
    public static final String REGEX_TENCENT_NUM          = "[1-9][0-9]{4,}";
    /**
     * 正则：中国邮政编码
     */
    public static final String REGEX_ZIP_CODE             = "[1-9]\\d{5}(?!\\d)";
    /**
     * 正则：正整数
     */
    public static final String REGEX_POSITIVE_INTEGER     = "^[1-9]\\d*$";
    /**
     * 正则：负整数
     */
    public static final String REGEX_NEGATIVE_INTEGER     = "^-[1-9]\\d*$";
    /**
     * 正则：整数
     */
    public static final String REGEX_INTEGER              = "^-?[1-9]\\d*$";
    /**
     * 正则：非负整数(正整数 + 0)
     */
    public static final String REGEX_NOT_NEGATIVE_INTEGER = "^[1-9]\\d*|0$";
    /**
     * 正则：非正整数（负整数 + 0）
     */
    public static final String REGEX_NOT_POSITIVE_INTEGER = "^-[1-9]\\d*|0$";
    /**
     * 正则：正浮点数
     */
    public static final String REGEX_POSITIVE_FLOAT       = "^[1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*$";
    /**
     * 正则：负浮点数
     */
    public static final String REGEX_NEGATIVE_FLOAT       = "^-[1-9]\\d*\\.\\d*|-0\\.\\d*[1-9]\\d*$";

    /**
     * 字母、数字和下划线字符
     */
    public static final String REGEX_LETTERS_NUMBER_ = "[a-zA-Z0-9_]+";

    /**
     * 验证中文字符
     */
    public static final String REGEX_ZH = "^[\\u4E00-\\u9FA5\\uF900-\\uFA2D]+$";

    /**
     * 字母与数字
     */
    public static final String REGEX_LETTERS_NUMBER = "^(?![0-9]+$)[A-Za-z0-9]{6,32}$";

    /**
     * 图片格式
     */
    public static final String REGEX_IMG = ".+(.JPEG|.jpeg|.JPG|.jpg|.png|.PNG)$";

    /**
     * 视频格式
     */
    public static final String REGEX_VIDEO = ".+(.MOV|.mov|.HEVC|.hevc|.avi|.AVI|.mp4|.MP4|.ogg|.OGG)$";


    /**
     * 正则验证基方法
     *
     * @param data  数据
     * @param regex 正则表达式
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkBase(String data, String regex) {
        if (StringUtils.isBlank(regex)){
            return false;
        }
        return Pattern.matches(regex, data);
    }

    /**
     * 验证身份证号
     *
     * @param idNumber 身份证号（15或18位）
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkIDNumber(String idNumber) {
        if (StringUtils.isBlank(idNumber)){
            return false;
        }
        return Pattern.matches(REGEX_ID_CARD, idNumber);
    }

    /**
     * 验证Email
     *
     * @param email email地址，格式：zhangsan@sina.com，zhangsan@xxx.com.cn，xxx代表邮件服务商
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkEmail(String email) {
        if (StringUtils.isBlank(email)){
            return false;
        }
        return Pattern.matches(REGEX_EMAIL, email);
    }

    /**
     * 验证中文字符
     *
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkChinese(String content) {
        if (StringUtils.isBlank(content)){
            return false;
        }
        return Pattern.matches(REGEX_ZH, content);
    }

    /**
     * 验证字母、数字和下划线字符
     *
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkAccount(String account) {
        if (StringUtils.isBlank(account)){
            return false;
        }
        return Pattern.matches(REGEX_LETTERS_NUMBER, account);
    }

    /**
     * 只能输入字母、字母与数字组合成的字符串
     *
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkKartorNo(String kartorNo) {
        if (StringUtils.isBlank(kartorNo)){
            return false;
        }
        return Pattern.matches(REGEX_LETTERS_NUMBER, kartorNo);
    }

    /**
     * 验证手机号码
     *
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkMobile(String mobile) {
        if (StringUtils.isBlank(mobile)){
            return false;
        }
        return Pattern.matches(REGEX_MOBILE_EXACT, mobile);
    }

    /**
     * 验证日期（年月日）
     *
     * @param birthday 日期，格式：1992-09-03，或1992.09.03
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkBirthday(String birthday) {
        if (StringUtils.isBlank(birthday)){
            return false;
        }
        String regex = "[1-9]{4}([-./])\\d{1,2}\\1\\d{1,2}";
        return Pattern.matches(regex, birthday);
    }

    /**
     * 验证车辆识别代号
     * 只能输入17位数字或者字母组成的字符串，首字符不能为0
     *
     * @param carFrameNo 车辆识别代号数据
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkCarFrameNo(String carFrameNo) {
        if (StringUtils.isBlank(carFrameNo)){
            return false;
        }
        String regex = "^[1-9a-zA-Z]{1}[0-9a-zA-Z]{16}$";
        return Pattern.matches(regex, carFrameNo);
    }

    /**
     * 验证行驶证档案编号
     * 只能输入数字
     *
     * @param carDrivingNo 行驶证档案编号数据
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkCarDrivingNo(String carDrivingNo) {
        if (StringUtils.isBlank(carDrivingNo)){
            return false;
        }
        String regex = "^[0-9]*$";
        return Pattern.matches(regex, carDrivingNo);
    }

    /**
     * 验证数字
     * 只能输入数字
     *
     * @param mileage 里程数据
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkNumber(String mileage) {
        if (StringUtils.isBlank(mileage)){
            return false;
        }
        String regex = "[0]|[1-9][0-9]{0,5}";
        return Pattern.matches(regex, mileage);
    }

    /**
     * 验证驾照档案编号
     * 12位的数字
     *
     * @param driverLicenseNo 驾照编号数据
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkDriverLicenseNo(String driverLicenseNo) {
        if (StringUtils.isBlank(driverLicenseNo)){
            return false;
        }
        String regex = "[0-9]{12}";
        return Pattern.matches(regex, driverLicenseNo);
    }

    /**
     * 车机号
     * 数字和字母的组合字符串
     *
     * @param vin 数据
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkVin(String vin) {
        if (StringUtils.isBlank(vin)){
            return false;
        }
        String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[a-zA-Z0-9]+$";
        return Pattern.matches(regex, vin);
    }

    /**
     * 车机id
     * 13位数字和字母的组合字符串
     *
     * @param id 数据
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkDeviceId(String id) {
        if (StringUtils.isBlank(id)){
            return false;
        }
        String regex = "^(?![0-9]{13}$)(?![a-zA-Z]{13}$)[a-zA-Z0-9]{13}$";
        return Pattern.matches(regex, id);
    }

    /**
     * 车机序列号
     *
     * @param sn 数据
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkDeviceSn(String sn) {
        if (StringUtils.isBlank(sn)){
            return false;
        }
        String regex = "^[0-9]+$";
        return Pattern.matches(regex, sn);
    }

    /**
     * 普通车牌格式验证
     */
    public static boolean checkCarPlate(String plate) {
        if (StringUtils.isBlank(plate)){
            return false;
        }
        String regex = "^[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z0-9]{5}$";
        return Pattern.matches(regex, plate);
    }

    /**
     * 特殊车牌格式验证
     */
    public static boolean checkSpecialCarPlate(String specialPlate) {
        if (StringUtils.isBlank(specialPlate)){
            return false;
        }
        String regex = "^.{4,10}$";
        return Pattern.matches(regex, specialPlate);
    }

    /**
     * 字符索引匹配规则
     */
    public static boolean checkCharacterIndex(String cIndex) {
        if (StringUtils.isBlank(cIndex)){
            return false;
        }
        String regex = "^[A-Z]$";
        return Pattern.matches(regex, cIndex);
    }

    /**
     * 校验是否是图片格式
     * @param fileName 文件名称
     * @return 判断结果
     */
    public static boolean checkImages(String fileName){
        if (StringUtils.isBlank(fileName)){
            return false;
        }
       return Pattern.matches(REGEX_IMG, fileName);
    }

    /**
     * 校验是否是视频格式
     * @param fileName 文件名称
     * @return 判断结果
     */
    public static boolean checkVideo(String fileName){
        if (StringUtils.isBlank(fileName)){
            return false;
        }
        return Pattern.matches(REGEX_VIDEO, fileName);
    }

}
