package com.craffic.convey.jobserver.generator;

import com.craffic.convey.common.utils.DateUtil;

import java.sql.Timestamp;
import java.util.Date;

import static com.craffic.convey.common.constant.ConveyConstant.MIN_DATE;

public class DateGenerator {

    public static void main(String[] args) {
        Date date = dateGenerator();
        System.out.println(DateUtil.dateToString(date, DateUtil.DATE_PATTERN_1));
    }

    public static Date dateGenerator(){
        long startTime = Timestamp.valueOf(MIN_DATE).getTime();
        long endTime = new Date().getTime();
        long diff = endTime - startTime + 1;
        Date randomDate = new Date(startTime + (long)(Math.random() * diff));
        return randomDate;
    }


}
