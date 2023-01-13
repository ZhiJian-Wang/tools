package com.wzj.tools.date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * @author ZhiJian.Wang
 * @package com.wzj.tools.date
 * @description 时间处理工具类
 * @date 2023-01-11 16:39
 */
public class DateTimeUtil {

    private static final Logger logger = LoggerFactory.getLogger(DateTimeUtil.class);

    private static final String YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
    private static final String YYYYMMDDHHMMSS_CHINESE = "yyyy年MM月dd日 HH时mm分ss秒";
    private static final String YYYYMMDDHHMMSSSSS = "yyyy-MM-dd HH:mm:ss.SSS";
    private static final String YYYYMMDD = "yyyy-MM-dd";
    private static final String YYYYMMDD_CHANESE = "yyyy年MM月dd日";
    private static final String HHMMSS = "HH:mm:ss";
    private static final String HHMMSS_CHINESE = "HH时mm分ss秒";

    /**
     * 获取格式化时间转换器
     *
     * @param formatStr 格式化的格式字符串
     * @return 时间转换器
     */
    public static DateTimeFormatter getDateTimeFormatter(String formatStr) {
        if (formatStr == null || "".equals(formatStr)) {
            formatStr = YYYYMMDDHHMMSS;
        }
        return DateTimeFormatter.ofPattern(formatStr);
    }

    /**
     * Date转换为LocalDateTime
     *
     * @param date Date时间
     * @return LocalDateTime时间
     */
    public static LocalDateTime convertLocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * LocalDateTime转换为Date
     *
     * @param localDateTime LocalDateTime时间
     * @return Date时间
     */
    public static Date convertDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取所需日期时间格式
     *
     * @param date      日期时间
     * @param formatStr 格式字符串
     * @return 格式化后字符串时间
     */
    public static String formatDateToString(Date date, String formatStr) {
        String dateStr = null;
        if (date != null) {
            LocalDateTime localDateTime = convertLocalDateTime(date);
            dateStr = formatLocalDateTimeToString(localDateTime, formatStr);
        }
        return dateStr;
    }

    /**
     * 获取所需日期时间格式
     *
     * @param localDateTime 日期时间
     * @param formatStr     格式字符串
     * @return 格式化后字符串时间
     */
    public static String formatLocalDateTimeToString(LocalDateTime localDateTime, String formatStr) {
        DateTimeFormatter dateTimeFormatter = getDateTimeFormatter(formatStr);
        String dateStr = null;
        if (localDateTime != null) {
            try {
                dateStr = dateTimeFormatter.format(localDateTime);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                throw new RuntimeException(e);
            }
        }
        return dateStr;
    }

    /**
     * 获取一天最初的时间0点0分0秒
     *
     * @return 0点0分0秒
     */
    public static String firstTime() {
        return getDateTimeFormatter(HHMMSS).format(LocalTime.MIN);
    }

    /**
     * 获取一天最后的时间23点59分59秒
     *
     * @return 23点59分59秒
     */
    public static String lastTime() {
        return getDateTimeFormatter(HHMMSS).format(LocalTime.MAX);
    }

    /**
     * 获取当前日期当前月第一天0点0分0秒
     *
     * @return 当前日期当前月第一天0点0分0秒
     */
    public static LocalDateTime firstDayTimeOfMonth() {
        LocalDate localDate = LocalDate.now();
        localDate = localDate.with(TemporalAdjusters.firstDayOfMonth());
        return localDate.atStartOfDay();
    }

    /**
     * 获取当前日期当指定月最后一天23点59分59秒
     *
     * @return 当前日期指定月最后一天23点59分59秒
     */
    public static LocalDateTime lastDayTimeOfMonth() {
        LocalDate localDate = LocalDate.now();
        localDate = localDate.with(TemporalAdjusters.lastDayOfMonth());
        return LocalDateTime.of(localDate, LocalTime.MAX);
    }

    /**
     * 获取当前日期当指定月第一天0点0分0秒
     *
     * @param month 添加的月份
     * @return 当前日期指定月第一天0点0分0秒
     */
    public static LocalDateTime firstDayTimeOfMonth(int month) {
        LocalDate localDate = LocalDate.now();
        localDate = localDate.plusMonths(month).with(TemporalAdjusters.firstDayOfMonth());
        return localDate.atStartOfDay();
    }

    /**
     * 获取当前日期当指定月最后一天23点59分59秒
     *
     * @param month 添加的月份
     * @return 当前日期指定月最后一天23点59分59秒
     */
    public static LocalDateTime lastDayTimeOfMonth(int month) {
        LocalDate localDate = LocalDate.now();
        localDate = localDate.plusMonths(month).with(TemporalAdjusters.lastDayOfMonth());
        return LocalDateTime.of(localDate, LocalTime.MAX);
    }

    /**
     * 获取当前日期当前月第一天0点0分0秒
     *
     * @return 当前日期当前月第一天0点0分0秒
     */
    public static String firstDayTimeOfMonthToString() {
        return formatLocalDateTimeToString(firstDayTimeOfMonth(), null);
    }

    /**
     * 获取当前日期当前月最后一天23点59分59秒
     *
     * @return 当前日期当前月最后一天23点59分59秒
     */
    public static String lastDayTimeOfMonthToString() {
        return formatLocalDateTimeToString(lastDayTimeOfMonth(), null);
    }

    /**
     * 获取当前日期当指定月第一天0点0分0秒
     *
     * @param month 添加的月份
     * @return 当前日期指定月第一天0点0分0秒
     */
    public static String firstDayTimeOfMonthToString(int month) {
        return formatLocalDateTimeToString(firstDayTimeOfMonth(month), null);
    }

    /**
     * 获取当前日期当指定月最后一天23点59分59秒
     *
     * @param month 添加的月份
     * @return 当前日期指定月最后一天23点59分59秒
     */
    public static String lastDayTimeOfMonthToString(int month) {
        return formatLocalDateTimeToString(lastDayTimeOfMonth(month), null);
    }


}
