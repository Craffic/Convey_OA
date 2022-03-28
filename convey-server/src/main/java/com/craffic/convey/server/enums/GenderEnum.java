package com.craffic.convey.server.enums;

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
