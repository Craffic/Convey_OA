package com.craffic.convey.jobserver.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: Liu Chengyan
 * 社会人员实体类
 */
@Data
public class Person implements Serializable {

    /**
     * 身份证号
     */
    private String idCardNo;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private String gender;

    /**
     * 出生日期
     */
    private Date birthDate;

    /**
     * 籍贯
     */
    private Long nativePlaceCode;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 职业编码
     */
    private Long professionCode;

    /**
     * 毕业学校编码
     */
    private Long granduteSchoolCode;

    /**
     * 创建时间
     */
    private Date createdDate;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 更新时间
     */
    private Date updateDate;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 户籍地址
     */
    private Long homeProvince;
    private Long homeCity;
    private Long homeArea;

    /**
     * 工作地址
     */
    private Long workProvince;
    private Long workCity;
    private Long workArea;

    public Person() {
    }

    public Person(String idCardNo, String name, String gender, Long nativePlaceCode, Date birthDate,
                  Long homeProvince, Long homeCity, Long homeArea, Long workProvince, Long workCity, Long workArea,
                  Integer age, Long professionCode, Long granduteSchoolCode, Date createdDate, String createdBy, Date updateDate, String updateBy) {
        this.idCardNo = idCardNo;
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.nativePlaceCode = nativePlaceCode;
        this.homeProvince = homeProvince;
        this.homeCity = homeCity;
        this.homeArea = homeArea;
        this.workProvince = workProvince;
        this.workCity = workCity;
        this.workArea = workArea;
        this.age = age;
        this.professionCode = professionCode;
        this.granduteSchoolCode = granduteSchoolCode;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.updateDate = updateDate;
        this.updateBy = updateBy;
    }
}
