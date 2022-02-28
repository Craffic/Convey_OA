package com.craffic.convey.jobserver.service;

import jdk.internal.dynalink.beans.StaticClass;
import org.apache.ibatis.javassist.Loader;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

@Component
public class RandomGenerator {

    public final static String MIN_DATE = "1920-01-01 00:00:00";

    /**
     * 生成随机年份（1920-当今时间年份）
     * @return
     */
    public static Date randomDate(){
        long startTime = Timestamp.valueOf(MIN_DATE).getTime();
        long endTime = new Date().getTime();
        long diff = endTime - startTime + 1;
        Date randomDate = new Date(startTime + (long)(Math.random() * diff));
        return randomDate;
    }

}
