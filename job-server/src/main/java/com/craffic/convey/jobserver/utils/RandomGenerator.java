package com.craffic.convey.jobserver.utils;

import com.craffic.convey.jobserver.constant.OAConstant;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.*;

@Component
public class RandomGenerator {

    public static void main(String[] args) {

        System.out.println("生成身份证号----------------------------");
        System.out.println(idCardNoGenerator(new Date(), 341502L, "M"));
        for (int i = 0; i < 1000; i++) {
            System.out.println(nameGenerator("F"));
        }
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

    public static String idCardNoSuffixGenerator(String gender){
        StringBuffer idCardNoSuffix = new StringBuffer();
        Random random = new Random();
        int temp = random.nextInt(100);
        if (temp < 10) {
            idCardNoSuffix.append("0").append(temp);
        } else {
            idCardNoSuffix.append(temp);
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
            idCardNoSuffix.append(maleList.get(randomNumFromList(maleList)));
        } else if ("F".equals(gender)){
            idCardNoSuffix.append(femaleList.get(randomNumFromList(femaleList)));
        }
        int lastBit = random.nextInt(11);
        if (lastBit >= 10) {
            idCardNoSuffix.append("X");
        } else {
            idCardNoSuffix.append(lastBit);
        }
        return idCardNoSuffix.toString();
    }

    public static String idCardNoGenerator(Date birthDay, Long areaCode, String gender){
        StringBuffer idCardNo = new StringBuffer();
        String strAreaCode = areaCode.toString();
        String strBirthDay = DateUtil.dateToString(birthDay, DateUtil.DATE_PATTERN_3);
        return idCardNo.append(strAreaCode).append(strBirthDay).append(idCardNoSuffixGenerator(gender)).toString();
    }

    public static String genderGenerator(){
        List<String> genderList = new ArrayList<>();
        genderList.add("F");
        genderList.add("M");
        int i = randomNumFromList(genderList);
        return genderList.get(i);
    }

    /**
     * 生成姓
     * @return
     */
    public static String nameGenerator(String gender){
        StringBuffer name = new StringBuffer();
        name.append(genFirstName());
        Random random = new Random();
        if ("M".equals(gender)) {
            int length = OAConstant.BOY_NAME.length();
            for (int i = 0; i < 2; i++) {
                name.append(OAConstant.BOY_NAME.charAt(random.nextInt(length)));
            }
        } else if ("F".equals(gender)) {
            int length = OAConstant.GIRL_NAME.length();
            for (int i = 0; i < 2; i++) {
                name.append(OAConstant.GIRL_NAME.charAt(random.nextInt(length)));
            }
        } else {
            return "";
        }

        return name.toString();
    }

    /**
     * 生成姓
     * @return
     */
    public static String genFirstName(){
        int length = OAConstant.FIRST_NAME.length();
        Random random = new Random();
        int index = random.nextInt(length);
        return OAConstant.FIRST_NAME.charAt(index) + "";
    }





}
