package com.craffic.convey.server.enums;

public enum WorkStatEnum implements BaseEnum<Integer>{
    LEAVE_WORK(0, "离职"),
    ON_WORK(1, "在职");

    Integer val;
    String desc;

    WorkStatEnum(Integer val, String desc) {
        this.val = val;
        this.desc = desc;
    }

    @Override
    public Integer val() {
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
    public static WorkStatEnum parseByValue(Integer value){
        for (WorkStatEnum workStatEnum : values()) {
            if (workStatEnum.val().equals(value)){
                return workStatEnum;
            }
        }
        return null;
    }
}
