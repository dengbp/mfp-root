package com.yr.net.util;


import org.apache.commons.lang3.StringUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
 *  日期时间工具类
 * </p>
 */
public class DateUtil {

  public String formatDate( Date date ) {
    return formatDateByFormat(date, "yyyy-MM-dd");
  }

  public static String formatDate1( Date date ) {
    return formatDateByFormat(date, "yyyyMMdd");
  }

  public static String formatDateByFormat( Date date, String format ) {
    String result = "";
    if(date != null) {
      try {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        result = sdf.format(date);
      }
      catch(Exception ex) {
        ex.printStackTrace();
      }
    }
    return result;
  }

  public static Date parseDate( java.sql.Date date ) {
    return date;
  }

  public static java.sql.Date parseSqlDate( Date date ) {
    if(date != null) {
      return new java.sql.Date(date.getTime());
    }
    else {
      return null;
    }
  }

  public static String format( Date date, String format ) {
    String result = "";
    try {
      if(date != null) {
        java.text.DateFormat df = new SimpleDateFormat(format);
        result = df.format(date);
      }
    }
    catch(Exception e) {}
    return result;
  }

  public static String format( Date date ) {
    return format(date, "yyyy/MM/dd");
  }

  public static String format1( Date date ) {
    return format(date, "yyyy-MM-dd");
  }

  public static String format2( Date date ) {
    return format(date, "yyyy.MM.dd");
  }

  public static int getYear( Date date ) {
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    return c.get(Calendar.YEAR);
  }

  public static int getMonth( Date date ) {
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    return c.get(Calendar.MONTH) + 1;
  }

  public static int getDay( Date date ) {
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    return c.get(Calendar.DAY_OF_MONTH);
  }

  public static int getHour( Date date ) {
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    return c.get(Calendar.HOUR_OF_DAY);
  }

  public static int getMinute( Date date ) {
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    return c.get(Calendar.MINUTE);
  }

  public static int getSecond( Date date ) {
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    return c.get(Calendar.SECOND);
  }

  public static long getMillis( Date date ) {
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    return c.getTimeInMillis();
  }

  public static int getWeek( Date date ) {
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
    dayOfWeek = dayOfWeek - 1;
    if(dayOfWeek == 0) {
      dayOfWeek = 7;
    }
    return dayOfWeek;
  }

  public static String getDate( Date date ) {
    return format(date, "yyyy/MM/dd");
  }

  public static String getDate( Date date, String formatStr ) {
    return format(date, formatStr);
  }

  public static String getTime( Date date ) {
    return format(date, "HH:mm:ss");
  }

  public static String getDateTime( Date date ) {
    return format(date, "yyyy-MM-dd HH:mm:ss");
  }

  /**
   * 日期相加
   * 
   * @param date
   *          Date
   * @param day
   *          int
   * @return Date
   */
  public static Date addDate( Date date, int day ) {
    Calendar c = Calendar.getInstance();
    c.setTimeInMillis(getMillis(date) + ( (long) day ) * 24 * 3600 * 1000);
    return c.getTime();
  }

  /**
   * 月份相加
   * 
   * @param date
   *          Date
   * @param months
   *          int
   * @return Date
   */
  public static Date addMonth( Date date, int months ) {
    Calendar c = Calendar.getInstance();
    c.add(Calendar.MONTH, months);
    return c.getTime();
  }

  /**
   * 日期相减
   * 
   * @param date
   *          Date
   * @param date1
   *          Date
   * @return int
   */
  public static int diffDate( Date date, Date date1 ) {
    return (int) ( ( getMillis(date) - getMillis(date1) ) / ( 24 * 3600 * 1000 ) );
  }

  /**
   * 日期相减（减天数）
   * 
   * @param date
   *          Date
   * @return int
   */
  public static Date diffDate( Date date, int day ) {
    Calendar c = Calendar.getInstance();
    c.setTimeInMillis(getMillis(date) - ( (long) day ) * 24 * 3600 * 1000);
    return c.getTime();
  }

  /**
   * 日期相减(返回秒值)
   * 
   * @param date
   *          Date
   * @param date1
   *          Date
   * @return int
   * @author
   */
  public static Long diffDateTime( Date date, Date date1 ) {
    return (Long) ( ( getMillis(date) - getMillis(date1) ) / 1000 );
  }
  
  /**
   * 日期相减(返回毫秒)
   * @param date
   * @param date1
   * @return
   */
  public static Long diffDateTimeMs( Date date, Date date1 ) {
	    return (Long) ( ( getMillis(date) - getMillis(date1) ) );
	  }

  public static Date getdate( String date ) throws Exception {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    return sdf.parse(date);
  }

  public static Date getdate1( String date ) throws Exception {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    return sdf.parse(date);
  }

  public static Date getMaxTimeByStringDate( String date ) throws Exception {
    String maxTime = date + " 23:59:59";
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    return sdf.parse(maxTime);
  }
  /**
   * 获得当前时间
   * 
   * @return
   */
  public static Date getCurrentDateTime() {
    Date date = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String result = DateUtil.getDateTime(date);
    try {
      return sdf.parse(result);
    }
    catch(ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;

  }

  public static String getCurrentDateTimeToStr() {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    return sdf.format(getCurrentDateTime());
  }
  public static String getCurrentDateTimeToStr2() {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    return sdf.format(getCurrentDateTime());
  }
  public static String getCurrentDateTimeToStr3() {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    return sdf.format(getCurrentDateTime());
  }

  public static Long getWmsupdateDateTime() {
    Calendar cl = Calendar.getInstance();

    return cl.getTimeInMillis();
  }

  public static Integer getLeftSeconds( String date ) throws Exception {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date condition = sdf.parse(date);
    long n = condition.getTime();
    long s = sdf.parse(getCurrentDateTimeToStr2()).getTime();
    // System.out.println("开始时间:"+date+"-->"+(int)((s-n)/1000));
    return (int) ( ( s - n ) / 1000 );
  }

  /**
   * 获得时间戳
   * 
   * @return
   * @throws Exception
   */
  public static String getTime() {
    Date date = new Date();
    return String.valueOf(date.getTime());
  }

  /**
   * 获得时间戳(精确到秒)
   * 
   * @return
   * @throws Exception
   */
  public static long getTimestamp() {
    Date date = new Date();
    return date.getTime() / 1000;
  }

  public static int isDate( Date dt1, Date dt2 ) {
    int r = 0;
    try {
      SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");

      Date time1 = sdf.parse(sdf.format(dt1));
      Date time2 = sdf.parse(sdf.format(dt2));
      // System.out.println(time1);
      // System.out.println(time2);
      if(time1.equals(time2)) {
        r = 0;
      }
      else if(time1.before(time2)) {
        r = -1;
      }
      else {
        r = 1;
      }
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
    return r;
  }

  /**
   * 将日期转换问星期
   * 
   * @param date2
   * @return
   * @throws ParseException
   */
  public String getWeekDay( String date2 ) throws ParseException {
    Calendar c = Calendar.getInstance();
    String weekday = "";
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date = sdf.parse(date2);
    c.setTime(date);
    switch(c.get(Calendar.DAY_OF_WEEK) - 1) {
      case 0:
        weekday = ( c.get(Calendar.MONTH) + 1 ) + "月" + c.get(Calendar.DAY_OF_MONTH) + "日  周日";
        break;
      case 1:
        weekday = ( c.get(Calendar.MONTH) + 1 ) + "月" + c.get(Calendar.DAY_OF_MONTH) + "日  周一";
        break;
      case 2:
        weekday = ( c.get(Calendar.MONTH) + 1 ) + "月" + c.get(Calendar.DAY_OF_MONTH) + "日  周二";
        break;
      case 3:
        weekday = ( c.get(Calendar.MONTH) + 1 ) + "月" + c.get(Calendar.DAY_OF_MONTH) + "日  周三";
        break;
      case 4:
        weekday = ( c.get(Calendar.MONTH) + 1 ) + "月" + c.get(Calendar.DAY_OF_MONTH) + "日  周四";
        break;
      case 5:
        weekday = ( c.get(Calendar.MONTH) + 1 ) + "月" + c.get(Calendar.DAY_OF_MONTH) + "日  周五";
        break;
      case 6:
        weekday = ( c.get(Calendar.MONTH) + 1 ) + "月" + c.get(Calendar.DAY_OF_MONTH) + "日  周六";
        break;
      default:
        break;
    }
    return weekday;
  }

  public Date getDateTime( String dateStr ) throws ParseException {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date = sdf.parse(dateStr);
    return date;
  }

  public static final String fullFormat = "yyyy-MM-dd HH:mm:ss";

  public static final String fullFormat2 = "yyyyMMddHHmmss";

  public static final String format = "yyyyMMdd";

  /**
   * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
   * 
   * @param strDate
   * @return
   */
  public static Date strToDateLong( String strDate, String format ) {
    if(StringUtils.isEmpty(strDate)) {
      return null;
    }
    SimpleDateFormat formatter = new SimpleDateFormat(format);
    try {
      return formatter.parse(strDate);
    }
    catch(ParseException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 传入天数获取过期时间
   * 
   * @param date
   * @return
   */
  public static Date getExpirationTime( Date date, int day ) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    // 日期加减计算
    cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH) + day);
    return cal.getTime();
  }

  /**
   * 传入MMDDhhmmss格式的日期，补上年份
   * 
   * @param time
   * @return
   */
  public static Date getDateFromMMddhhmiss( String time ) {
    if(time == null || time.trim().length() == 0) {
      return null;
    }
    int year = getYear(new Date());
    String temp = year + time;
    Date retDate = DateUtil.strToDateLong(temp, DateUtil.fullFormat2);
    int is = isDate(retDate, new Date());
    if(is == 1) {
      year = year - 1;
    }
    temp = year + time;
    retDate = DateUtil.strToDateLong(temp, DateUtil.fullFormat2);
    return retDate;
  }

  /**
   * time秒后时间
   */
  public static String nextTime( Long time ) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    return sdf.format(( getTimestamp() + time ) * 1000);
  }

  /**
   * time秒前时间
   */
  public static String beforeTime( Long time ) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    return sdf.format(( getTimestamp() - time ) * 1000);
  }

  /**
   * 第二天开始时间
   */
  public static String tomorrowStart() {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
    return sdf.format(DateUtil.addDate(getCurrentDateTime(), 1));
  }

  /**
   * 第二天结束时间
   */
  public static String tomorrowEnd() {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
    return sdf.format(DateUtil.addDate(getCurrentDateTime(), 1));
  }

  /**
   * 一天的开始时间
   */
  public static String dayStart( Date date ) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
    return sdf.format(DateUtil.addDate(date, 0));
  }

  /**
   * 一天的结束时间
   */
  public static String dayEnd( Date date ) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
    return sdf.format(DateUtil.addDate(date, 0));
  }

  /**
   * 一天的开始时间
   * 
   * @throws ParseException
   */
  public static Date dayStartForDate( Date date ) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date dates = null;
    try {
      dates = sdf.parse(dayStart(date));
    }
    catch(ParseException e) {
      e.printStackTrace();
    }
    return dates;
  }

  /**
   * 一天的结束时间
   * 
   * @throws ParseException
   */
  public static Date dayEndForDate( Date date ) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date dates = null;
    try {
      dates = sdf.parse(dayEnd(date));
    }
    catch(ParseException e) {
      e.printStackTrace();
    }
    return dates;
  }

  /**
   * 获取整点时间
   */
  public static String getHourTime( Date date, int hour ) {
    if(hour < 10) {
      return format1(date) + "_0" + hour + ":00:00";
    }
    else {
      return format1(date) + "_" + hour + ":00:00";
    }
  }

  /**
   * 把毫秒转化成日期
   * 
   * @param dateFormat(日期格式，例如：MM/
   *          dd/yyyy HH:mm:ss)
   * @param millSec(毫秒数)
   * @return
   */
  public static String transferLongToDate( Long millSec, String dateFormat ) {
    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
    Date date = new Date(millSec);
    return sdf.format(date);
  }

  /**
   * @Description：判断某个时间是否在2个时间段内
   * @param startTime 开始时间
   * @param endTime 结束时间
   * @param minddleTime 中时间
   * @return: 返回结果描述
   * @return boolean: 返回值类型
   * @throws
   */
  public static boolean isNowTimeInTimePeriod( Date startTime, Date endTime, Date minddleTime ) {
    Calendar c1 = Calendar.getInstance();
    c1.setTime(startTime);
    Calendar c2 = Calendar.getInstance();
    c2.setTime(endTime);
    Calendar c3 = Calendar.getInstance();
    c3.setTime(minddleTime);
    boolean b = c3.getTimeInMillis() > c1.getTimeInMillis() && c3.getTimeInMillis() < c2.getTimeInMillis();
    return b;
  }

  /**
   * @Description：判断2个时间是否在同一天
   * @param args: 返回结果描述
   * @return void: 返回值类型
   * @throws
   */
  public static boolean judgeTimeInSameDay( Date date1, Date date2 ) {
    Calendar c1 = Calendar.getInstance();
    c1.setTime(date1);
    Calendar c2 = Calendar.getInstance();
    c2.setTime(date2);
    boolean b = c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH);
    return b;
  }
  
  /** 
   * 得到几天前的时间 
   *  
   * @param date 
   * @param days 
   * @return 
   */  
  public static Date getDateBefore(Date date, int days) {  
      Calendar c = Calendar.getInstance();  
      c.setTime(date);  
      c.set(Calendar.DATE, c.get(Calendar.DATE) - days);  
      return c.getTime();  
  } 

  public static void main( String[] args ) {
    try {
      // System.out.println(getLeftSeconds("2011-12-29 23:33:09"));
      // System.out.println(Math.abs(-1110));
      // System.out.println(transferLongToDate(1449298890000L, "yyyy-MM-dd
      // HH:mm:ss"));
      // System.out.println(transferLongToDate(1449298910000L, "yyyy-MM-dd
      // HH:mm:ss"));
      // String time = "0119135442";

      // System.out.println(dayStart(new Date()));
      // System.out.println(dayEnd(new Date()));
      // System.out.println(formatDate1(new Date()));
      // System.out.println(dayStartForDate(new Date()) + " : " +
      // dayEndForDate(new Date()));
      /*
       * SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       * Date startTime = format.parse("2016-01-05 12:38:00");
       * Date middleTime = format.parse("2016-01-23 15:56:00");
       * Date endTime = format.parse("2016-01-23 18:56:00");
       * System.out.println(isNowTimeInTimePeriod(startTime, endTime,
       * middleTime));
       * System.out.println(judgeTimeInSameDay(addDate(startTime, 9), new
       * Date()));
       */
    	Date d=new Date();
    	System.out.println(DateUtil.getMillis(d));
    	System.out.println(d.getTime());
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  /**
   * 判断传入的字符串是否能按指定格式转成日期
   * 
   * @param format
   *          格式，如"yyyy-MM-dd"
   * @param dateStr
   *          要转的字符串
   * @return
   */
  public static boolean ifDateStringLegal( String format, String dateStr ) {
    SimpleDateFormat sdf = new SimpleDateFormat(format);
    try {
      sdf.parse(dateStr);
    }
    catch(Exception e) {
      return false;
    }
    return true;
  }
  
  /** 
   * 时间戳转换成日期格式字符串 
   * @param seconds 精确到秒的字符串 
   * @param formatStr 
   * @return 
   */  
  public static String timeStamp2Date(String seconds,String format) {  
      if(seconds == null || seconds.isEmpty() || seconds.equals("null")){  
          return "";  
      }  
      if(format == null || format.isEmpty()) format = "yyyy-MM-dd HH:mm:ss";  
      SimpleDateFormat sdf = new SimpleDateFormat(format);
      if(seconds.length()==10){
    	  seconds+="000";
      }
      return sdf.format(new Date(Long.valueOf(seconds)));  
  }  
  
  /** 
   * 日期格式字符串转换成时间戳 
   * @param date 字符串日期 
   * @param format 如：yyyy-MM-dd HH:mm:ss 
   * @return 
   */  
  public static String date2TimeStamp(String date_str,String format){  
      try {  
          SimpleDateFormat sdf = new SimpleDateFormat(format);  
          return String.valueOf(sdf.parse(date_str).getTime()/1000);  
      } catch (Exception e) {  
          e.printStackTrace();  
      }  
      return "";  
  }  
    
  /** 
   * 取得当前时间戳（精确到秒） 
   * @return 
   */  
  public static String timeStamp(){  
      long time = System.currentTimeMillis();  
      String t = String.valueOf(time/1000);  
      return t;  
  }  

}
