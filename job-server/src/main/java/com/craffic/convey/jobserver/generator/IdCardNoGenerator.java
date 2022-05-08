package com.craffic.convey.jobserver.generator;

import com.craffic.convey.common.constant.ConveyConstant;
import com.craffic.convey.common.enums.GenderEnum;
import com.craffic.convey.common.utils.DateUtil;
import com.craffic.convey.jobserver.utils.RandomNumUtil;

import java.time.LocalDate;
import java.util.Date;

/**
 * 身份证号生成器
 */
public class IdCardNoGenerator {

    public static void main(String[] args) {
        String idCardNo = generate(DateUtil.localDateToDate(LocalDate.of(1991, 7, 29)), 440183L, GenderEnum.MALE);
        System.out.println(idCardNo);
    }

    /**
     * 生成身份证号
     * @param birthDate 出生年月日（YYYYMMdd）
     * @param areaCode 地区编码
     * @param gender 性别
     * @return 身份证号
     */
    public static String generate(Date birthDate, Long areaCode, GenderEnum gender){
        StringBuffer idCardNo = new StringBuffer();
        return idCardNo.append(areaCode).append(DateUtil.dateToString(birthDate, DateUtil.DATE_PATTERN_3)).append(idCardNoSuffix(gender)).toString();
    }

    /**
     * 生成身份证后四位
     * @param genderEnum
     * @return
     */
    private static String idCardNoSuffix(GenderEnum genderEnum){
        StringBuffer idCardNoSuffix = new StringBuffer();
        // 生成前3位
        int number = RandomNumUtil.randomRangeNumber(100);
        idCardNoSuffix = number < 10 ? idCardNoSuffix.append(0).append(number) : idCardNoSuffix.append(idCardNoSuffix);
        // 倒数第二位是性别位
        switch (genderEnum) {
            case MALE:
                Integer index = RandomNumUtil.randomNumFormList(ConveyConstant.maleNumList);
                Integer obj = ConveyConstant.maleNumList.get(index);
                idCardNoSuffix.append(obj);
                break;
            case FEMALE:
                idCardNoSuffix.append(ConveyConstant.feMaleNumList.get(RandomNumUtil.randomNumFormList(ConveyConstant.feMaleNumList)));
                break;
        }
        // 最后一位是0-10的随机数，10用X来代替
        int lastBit = RandomNumUtil.randomRangeNumber(11);
        idCardNoSuffix = lastBit >= 10 ? idCardNoSuffix.append("X") : idCardNoSuffix.append(lastBit);
        return idCardNoSuffix.toString();
    }

}
