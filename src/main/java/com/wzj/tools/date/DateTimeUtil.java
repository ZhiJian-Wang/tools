package com.wzj.tools.date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @author ZhiJian.Wang
 * @package com.wzj.tools.date
 * @description 时间处理工具类
 * @date 2023-01-11 16:39
 */
public class DateTimeUtil {

    private static final Logger logger = LoggerFactory.getLogger(DateTimeUtil.class);

    private static final String YYYYMMDDHHMMSS="yyyy-MM-dd HH:mm:ss";
    private static final String YYYYMMDDHHMMSS_CHINESE="yyyy年MM月dd日 HH时mm分ss秒";
    private static final String YYYYMMDDHHMMSSSSS="yyyy-MM-dd HH:mm:ss.SSS";
    private static final String YYYYMMDD="yyyy-MM-dd";
    private static final String YYYYMMDD_CHANESE="yyyy年MM月dd日";
    private static final String HHMMSS_CHINESE="HH时mm分ss秒";

    public static DateTimeFormatter getDateTimeFormatter(String formatStr){
        if (formatStr==null||"".equals(formatStr)){
            formatStr=YYYYMMDDHHMMSS;
        }
        return DateTimeFormatter.ofPattern(formatStr);
    }

    public static LocalDateTime getLocalDateTime(Date date){
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static Date getDate(LocalDateTime localDateTime){
        return Date.from(localDateTime.atZone( ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取所需日期时间格式
     * @param date 日期时间
     * @param formatStr 格式字符串
     * @return 格式化后字符串时间
     */
    public static String formatDateToString(Date date,String formatStr) {
        String dateStr = null;
        if (date!=null){
            LocalDateTime localDateTime=getLocalDateTime(date);
            dateStr=formatDateTimeToString(localDateTime,formatStr);
        }
        return dateStr;
    }

    /**
     * 获取所需日期时间格式
     * @param localDateTime 日期时间
     * @param formatStr 格式字符串
     * @return 格式化后字符串时间
     */
    public static String formatDateTimeToString(LocalDateTime localDateTime, String formatStr) {
        DateTimeFormatter dateTimeFormatter = getDateTimeFormatter(formatStr);
        String dateStr = null;
        if (localDateTime!=null){
            try {
                dateStr = dateTimeFormatter.format(localDateTime);
            } catch (Exception e) {
                logger.error(e.getMessage(),e);
                throw new RuntimeException(e);
            }
        }
        return dateStr;
    }

    public static Date getDateOfFirst(){
        return getDate(getDateTimeOfFirst());
    }

    public static Date getDateOfFirst(int month){
        return getDate(getDateTimeOfFirst(month));
    }

    public static LocalDateTime getDateTimeOfFirst(){
        LocalDate localDate=LocalDate.now();
        localDate=localDate.withDayOfMonth(1);
        return localDate.atStartOfDay();
    }

    public static LocalDateTime getDateTimeOfFirst(int month){
        LocalDate localDate=LocalDate.now();
        localDate=localDate.plusMonths(month).withDayOfMonth(1);
        return localDate.atStartOfDay();
    }

    public static String getDateTimeStringOfFirst(){
        return formatDateTimeToString(getDateTimeOfFirst(),null);
    }

    public static String getDateTimeStringOfFirst(int month){
        return formatDateTimeToString(getDateTimeOfFirst(month),null);
    }

}
