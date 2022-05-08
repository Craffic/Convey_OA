package com.craffic.convey.jobserver.generator;

import com.craffic.convey.common.enums.GenderEnum;
import com.craffic.convey.jobserver.constant.OAConstant;
import com.craffic.convey.jobserver.utils.RandomNumUtil;

public class NameGenerator {

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            GenderEnum generate = GenderGenerator.generate();
            String fullName = getFullName(generate);
            System.out.println(fullName);
        }

    }

    public static String getFullName(GenderEnum gender){
        StringBuffer fullName = new StringBuffer();
        fullName.append(genFirstName());
        int length;
        switch (gender) {
            case MALE:
                length = OAConstant.BOY_NAME.length();
                for (int i = 0; i < 2; i++) {
                    fullName.append(OAConstant.BOY_NAME.charAt(RandomNumUtil.randomRangeNumber(length)));
                }
                break;
            case FEMALE:
                length = OAConstant.GIRL_NAME.length();
                for (int i = 0; i < 2; i++) {
                    fullName.append(OAConstant.GIRL_NAME.charAt(RandomNumUtil.randomRangeNumber(length)));
                }
                break;
        }
        return fullName.toString();
    }

    /**
     * 生成姓
     */
    public static String genFirstName(){
        int len = OAConstant.FIRST_NAME.length();
        int index = RandomNumUtil.randomRangeNumber(len);
        StringBuffer firstName = new StringBuffer();
        return firstName.append(OAConstant.FIRST_NAME.charAt(index)).toString();
    }


}
