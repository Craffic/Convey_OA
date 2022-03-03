package com.craffic.convey.jobserver.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 日期时间工具类
 */
public class DateUtil {
    public final static String DATE_PATTERN_1 = "YYYY-MM-dd";
    public final static String DATE_PATTERN_2 = "YYYY/MM/dd";
    public final static String DATE_PATTERN_3 = "YYYYMMdd";


    public static void main(String[] args) {
        LocalDate localDate = dateToLocalDate(new Date());
        System.out.println(localDate);
        System.out.println(localDateToString(localDate, DATE_PATTERN_1));
        System.out.println(localDateToString(localDate, DATE_PATTERN_2));
        System.out.println(localDateToString(localDate, DATE_PATTERN_3));
        System.out.println(dateToString(new Date(), DATE_PATTERN_1));
        System.out.println(dateToString(new Date(), DATE_PATTERN_2));
        System.out.println(dateToString(new Date(), DATE_PATTERN_3));
        try {
            System.out.println(strToLocalDate("2022-03-01"));
        } catch (Exception e) {
            System.out.println("转换日期出错");
        }
    }

    /**
     * Date转LocalDate
     * @param date
     * @return
     */
    public static LocalDate dateToLocalDate(Date date){
        // 将该日期对象转换为Instant，然后返回Instant表示与日期对象在tim线上的同一点
        Instant instant = date.toInstant();
        // 获取系统默认时区
        ZoneId zoneId = ZoneId.systemDefault();
        // atZone()方法返回在指定时区从此Instant生成的ZonedDateTime
        return instant.atZone(zoneId).toLocalDate();
    }

    /**
     * Date转String
     * @return
     */
    public static String dateToString(Date date, String datePattern){
        SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
        return dateFormat.format(date);
    }

    /**
     * LocalDate转String
     * @return
     */
    public static String localDateToString(LocalDate localDate, String datePattern){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
        return localDate.format(formatter);
    }

    /**
     * LocalDate转Date
     * @param localDate
     * @return
     */
    public static Date localDateToDate(LocalDate localDate){
        Instant instant = localDate.atTime(LocalTime.MIDNIGHT).atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    /**
     * String convert to LocalDate
     * 注意：参数strDate一定要YYYY-MM-dd格式，否则报错
     * @param strDate
     * @return
     */
    public static LocalDate strToLocalDate(String strDate) throws Exception {
        try {
            LocalDate localDate = LocalDate.parse(strDate);
            return localDate;
        } catch (Exception e) {
            throw new Exception("转换LocalDate发生异常！");
        }
    }

    /**
     * 计算两个日期间相隔年数
     * @param date1
     * @param date2
     * @return
     */
    public static int calYearDiff(Date date1, Date date2){
        LocalDate localDate1 = dateToLocalDate(date1);
        LocalDate localDate2 = dateToLocalDate(date2);
        int month1 = localDate1.getMonth().getValue();
        int month2 = localDate2.getMonth().getValue();
        int month = (localDate1.getYear() - localDate2.getYear()) * 12 + (month1) - month2;
        return month / 12;
    }
}
