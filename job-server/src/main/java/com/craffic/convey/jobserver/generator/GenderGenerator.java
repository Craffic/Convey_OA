package com.craffic.convey.jobserver.generator;

import com.craffic.convey.common.enums.GenderEnum;
import com.craffic.convey.jobserver.utils.RandomNumUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 性别生成器
 */
public class GenderGenerator {

    /**
     * 生成性别
     * @return
     */
    public static GenderEnum generate(){
        List<GenderEnum> genderList = GenderEnum.getAllGenderEnum();
        Integer num = RandomNumUtil.randomNumFormList(genderList);
        return genderList.get(num);
    }
}
