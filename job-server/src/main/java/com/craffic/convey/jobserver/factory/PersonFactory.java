package com.craffic.convey.jobserver.factory;

import com.craffic.convey.common.constant.ConveyConstant;
import com.craffic.convey.common.enums.GenderEnum;
import com.craffic.convey.common.utils.DateUtil;
import com.craffic.convey.jobserver.generator.*;
import com.craffic.convey.jobserver.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class PersonFactory {

    @Autowired
    private DictGenerator dictGenerator;

    /**
     * 利用工厂模式创建一个人员实例
     * @return Person
     */
    public Person generatePersonInstance(){
        // 性别
        GenderEnum genderEnum = GenderGenerator.generate();
        // 姓名
        String fullName = NameGenerator.getFullName(genderEnum);
        // 生成用户籍贯省市区
        Long homeProvinceCode = dictGenerator.dictGenerator(ConveyConstant.PROVINCE);
        Long homeCityCode = dictGenerator.dictGenerator(ConveyConstant.CITY);
        Long homeAreaCode = dictGenerator.dictGenerator(ConveyConstant.AREA);

        // 出生日期
        Date birthDate = DateGenerator.dateGenerator();
        // 年龄
        int age = DateUtil.calYearDiff(new Date(), birthDate);
        // 如果小于14岁或者大于60岁，不能有工作地址
        Long workProvinceCode = null;
        Long workCityCode = null;
        Long workAreaCode = null;
        if (age > 15 && age < 61) {
            workCityCode = dictGenerator.dictGenerator(ConveyConstant.CITY);
            workProvinceCode = dictGenerator.dictGenerator(ConveyConstant.PROVINCE);
            workAreaCode = dictGenerator.dictGenerator(ConveyConstant.AREA);
        }
        // 如果大于18岁，才允许有大学院校
        Long professionalCode = null;
        Long universityCode = null;
        if (age > 18) {
            // 生成专业和学校
            professionalCode = dictGenerator.dictGenerator(ConveyConstant.PROFESSIONAL);
            universityCode = dictGenerator.dictGenerator(ConveyConstant.UNIVERSITY);
        }
        // 生成身份证号
        String idCardNo = IdCardNoGenerator.generate(birthDate, homeAreaCode, genderEnum);
        return new Person(idCardNo, fullName, genderEnum.val(), homeAreaCode, birthDate,
                          homeProvinceCode, homeCityCode, homeAreaCode, workProvinceCode, workCityCode, workAreaCode,
                          age, professionalCode, universityCode, new Date(), ConveyConstant.SYSTEM, new Date(), ConveyConstant.SYSTEM
                );
    }

}