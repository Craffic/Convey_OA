package com.craffic.convey.common.constant;

import com.google.common.collect.ImmutableList;

import java.util.List;

public class ConveyConstant {

    /**
     * 省市区常量
     */
    public static final String PROVINCE = "PROVINCE";
    public static final String CITY = "CITY";
    public static final String AREA = "AREA";

    /**
     * 专业
     */
    public static final String PROFESSIONAL = "PROFESSIONAL";

    /**
     * 学校
     */
    public static final String UNIVERSITY = "UNIVERSITY";

    /**
     * 系统用户常量
     */
    public static final String SYSTEM = "SYSTEM";

    public final static String MIN_DATE = "1920-01-01 00:00:00";

    /**
     * 男女性身份证奇偶数
     */
    public static List<Integer> maleNumList = ImmutableList.of(1, 3, 5, 7, 9);
    public static List<Integer> feMaleNumList = ImmutableList.of(0, 2, 4, 6, 8);

    public static List<Long> qualityProfession = ImmutableList.of(80901L,80902L,80903L,80904L,81001L,81002L);
}
