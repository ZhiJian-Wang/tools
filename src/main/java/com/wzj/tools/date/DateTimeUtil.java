package com.wzj.tools.date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author ZhiJian.Wang
 * @package com.wzj.tools.date
 * @description 时间处理工具类
 * @date 2023-01-11 16:39
 */
public class DateTimeUtil {

    private static final Logger logger = LoggerFactory.getLogger(DateTimeUtil.class);

    private static final String YYYY_MM_DD_HH_MM_SS="yyyy-MM-dd HH:mm:ss";
    private static final String YYYY_MM_DD="yyyy-MM-dd";
    private static final String HH_MM_SS="HH:mm:ss";

    private static SimpleDateFormat getSimpleDateFormat(String formatStr){
        if (formatStr==null||"".equals(formatStr)){
            formatStr=YYYY_MM_DD_HH_MM_SS;
        }
        return new SimpleDateFormat(formatStr);
    }

    private static DateTimeFormatter getDateTimeFormatter(String formatStr){
        if (formatStr==null||"".equals(formatStr)){
            formatStr=YYYY_MM_DD_HH_MM_SS;
        }
        return DateTimeFormatter.ofPattern(formatStr);
    }

    /**
     * 获取所需日期时间格式
     * @param date 日期时间
     * @param formatStr 格式字符串
     * @return 格式化后字符串时间
     */
    public static String formatDateTimeToString(Date date,String formatStr) {
        SimpleDateFormat simpleDateFormat = getSimpleDateFormat(formatStr);
        String dateStr = null;
        try {
            dateStr = simpleDateFormat.format(date);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new RuntimeException(e);
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
        try {
            dateStr = dateTimeFormatter.format(localDateTime);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new RuntimeException(e);
        }
        return dateStr;
    }
}
