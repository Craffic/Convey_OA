package com.craffic.convey.jobapi.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PersonVo implements Serializable {
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

    private String genderDesc;

    /**
     * 出生日期
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private java.util.Date birthDate;

    /**
     * 籍贯
     */
    private Long nativePlaceCode;

    private String nativePlaceDesc;

    /**
     * 家庭地址
     */
    private String homeAddress;
    private Long homeProvince;
    private Long homeCity;
    private Long homeArea;

    /**
     * 工作地址
     */
    private String workAddress;
    private Long workProvince;
    private Long workCity;
    private Long workArea;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 职业编码
     */
    private Long professionCode;

    private String professionDesc;

    /**
     * 毕业学校编码
     */
    private Long granduteSchoolCode;

    private String granduteSchoolDesc;

    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyyMMdd",timezone="GMT+8")
    private Date createdDate;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 更新时间
     */
    @JsonFormat(pattern="yyyyMMdd",timezone="GMT+8")
    private Date updateDate;
}
