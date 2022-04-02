package com.craffic.convey.jobserver.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class PersonVo {
    /**
     * 身份证号
     */
    private String idcardNo;

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
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date birthDate;

    /**
     * 籍贯
     */
    private Long nativePlaceCode;

    /**
     * 家庭地址
     */
    private String homeAddress;

    /**
     * 工作地址
     */
    private String workAddress;

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
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date createdDate;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 更新时间
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date updateDate;
}
