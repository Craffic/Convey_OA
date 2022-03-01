package com.craffic.convey.jobserver.utils;

import jdk.internal.dynalink.beans.StaticClass;
import org.apache.ibatis.javassist.Loader;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.*;

@Component
public class RandomGenerator {

    public static void main(String[] args) {

        System.out.println("生成身份证号----------------------------");
        System.out.println(idCardNoGenerator(new Date(), 341502L, "M"));
    }

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

    /**
     * 取数组的随机位数
     */
    public static int randomNumFromList(List list){
        int size = list.size();
        Random random = new Random();
        return random.nextInt(size);
    }

    public static String idCardNoSufixGenerator(String gender){
        StringBuffer idCardNoSuffex = new StringBuffer();
        Random random = new Random();
        int temp = random.nextInt(100);
        if (temp < 10) {
            idCardNoSuffex.append("0").append(temp);
        } else {
            idCardNoSuffex.append(temp);
        }
        List<String> maleList = new ArrayList<>();
        maleList.add("1");
        maleList.add("3");
        maleList.add("5");
        maleList.add("7");
        maleList.add("9");
        List<String> femaleList = new ArrayList<>();
        femaleList.add("0");
        femaleList.add("2");
        femaleList.add("4");
        femaleList.add("6");
        femaleList.add("8");

        if ("M".equals(gender)) {
            idCardNoSuffex.append(maleList.get(randomNumFromList(maleList)));
        } else if ("F".equals(gender)){
            idCardNoSuffex.append(femaleList.get(randomNumFromList(femaleList)));
        }
        int lastBit = random.nextInt(11);
        if (lastBit >= 10) {
            idCardNoSuffex.append("X");
        } else {
            idCardNoSuffex.append(lastBit);
        }
        return idCardNoSuffex.toString();
    }

    public static String idCardNoGenerator(Date birthDay, Long areaCode, String gender){
        StringBuffer idCardNo = new StringBuffer();
        String strAreaCode = areaCode.toString();
        String strBirthDay = DateUtil.dateToString(birthDay, DateUtil.DATE_PATTERN_3);
        return idCardNo.append(strAreaCode).append(strBirthDay).append(idCardNoSufixGenerator(gender)).toString();
    }

    public static String genderGenerator(){
        List<String> genderList = new ArrayList<>();
        genderList.add("F");
        genderList.add("M");
        int i = randomNumFromList(genderList);
        return genderList.get(i);
    }


}
