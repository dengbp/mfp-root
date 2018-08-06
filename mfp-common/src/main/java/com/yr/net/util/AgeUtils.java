package com.yr.net.util;

import java.util.Calendar;
import java.util.Date;

/**
 * All rights Reserved, Designed By SEGI
 * <pre>
 * Copyright:  Copyright(C) 2018
 * Company:    SEGI.
 * @Author:     dengbp
 * @CreateDate: 2018/6/9
 * </pre>
 * <p>
 *     根据生日计算年龄
 * </p>
 */
public class AgeUtils {
    private static final int invalidAge = -1;//非法的年龄，用于处理异常。

    public static Integer getAgeByDate(Date birthday) {
        Calendar calendar = Calendar.getInstance();

        if (calendar.getTimeInMillis() - birthday.getTime() < 0L) {
            return invalidAge;
        }
        int yearNow = calendar.get(Calendar.YEAR);
        int monthNow = calendar.get(Calendar.MONTH);
        int dayOfMonthNow = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.setTime(birthday);
        int yearBirthday = calendar.get(Calendar.YEAR);
        int monthBirthday = calendar.get(Calendar.MONTH);
        int dayOfMonthBirthday = calendar.get(Calendar.DAY_OF_MONTH);
        int age = yearNow - yearBirthday;
        if (monthNow <= monthBirthday && monthNow == monthBirthday && dayOfMonthNow < dayOfMonthBirthday || monthNow < monthBirthday) {
            age--;
        }
        return new Integer(age);
    }
}
