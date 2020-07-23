package com.laoxu.demo.util;


import com.laoxu.demo.exception.BizRuntimeException;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;


/**
 * @Author: YLBG-LDH-1506
 * @Description:
 * @Date: 2017/08/09
 */
public abstract class DateUtils {

    public static final String FORMAT_DELETE_TIME = "yyyyMMddHHmmssSSS";

    public static final String FORMAT_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    public static final String FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String FORMAT_YYYY_MM_DD_HH = "yyyy-MM-dd HH";
    public static final String FORMAT_YYYY_MM= "yyyy-MM";

    private DateUtils() {
    }

    /**
     * 获取删除时间
     * @return
     */
    public static String getDeleteTime() {
        return DateFormatUtils.format(new Date(), FORMAT_DELETE_TIME);
    }

    /**
     * date->string 使用pattern
     * @param date
     * @param pattern
     * @return
     */
    public static String formatDate(Date date, final String pattern) {
        return DateFormatUtils.format(date, pattern);
    }

    /**
     * string -> date 使用pattern
     * @param dateTime
     * @param pattern
     * @return
     */
    public static Date parse(String dateTime, String pattern) {
        try {
            return org.apache.commons.lang3.time.DateUtils.parseDate(dateTime, pattern);
        } catch (ParseException e) {
            throw new BizRuntimeException(e);
        }
    }

    /**
     * string -> date 会从patterns选来转换，如果全部无法转换就会报错
     * @param dateTime
     * @param patterns
     * @return
     */
    public static Date parse(String dateTime, String[] patterns) {
        try {
            return org.apache.commons.lang3.time.DateUtils.parseDate(dateTime, patterns);
        } catch (ParseException e) {
            throw new BizRuntimeException(e);
        }
    }

    /**
     * 比较2个date类型时间是否是同一天, 传null，返回false
     * @param d1
     * @param d2
     * @return
     */
    public static boolean sameDate(Date d1, Date d2) {
        if (d1 == null || d2 == null) {
            return false;
        }
        LocalDate localDate1 = ZonedDateTime.ofInstant(d1.toInstant(), ZoneId.systemDefault())
                .toLocalDate();
        LocalDate localDate2 = ZonedDateTime.ofInstant(d2.toInstant(), ZoneId.systemDefault())
                .toLocalDate();
        return localDate1.isEqual(localDate2);
    }
}
