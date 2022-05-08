package com.craffic.convey.common.enums;

import java.util.ArrayList;
import java.util.List;

public enum GenderEnum implements BaseEnum<String>{
    MALE("M", "男"),
    FEMALE("F", "女");

    String val;
    String desc;

    GenderEnum(String val, String desc) {
        this.val = val;
        this.desc = desc;
    }

    @Override
    public String val() {
        return val;
    }

    @Override
    public String desc() {
        return desc;
    }

    public static List<GenderEnum> getAllGenderEnum(){
        List<GenderEnum> genderEnumList = new ArrayList();
        genderEnumList.add(GenderEnum.MALE);
        genderEnumList.add(GenderEnum.FEMALE);
        return genderEnumList;
    }

    /**
     * 根据value得到对用的enum
     * @param value
     * @return
     */
    public static GenderEnum parseByValue(String value){
        for (GenderEnum workStatEnum : values()) {
            if (workStatEnum.val().equals(value)){
                return workStatEnum;
            }
        }
        return null;
    }
}
