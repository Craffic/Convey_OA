package com.craffic.convey.jobapi.req;

import com.craffic.convey.common.request.BaseQuery;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class PersonReq extends BaseQuery {

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
    @DateTimeFormat(pattern="yyyyMMdd")
    private Date birthDate;

    /**
     * 查询出生日期范围
     */
    @DateTimeFormat(pattern="yyyyMMdd")
    private Date birthDateStart;

    /**
     * 查询出生日期范围
     */
    @DateTimeFormat(pattern="yyyyMMdd")
    private Date birthDateEnd;

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
     private Integer ageStart;

     private Integer ageEnd;

    /**
     * 职业编码
     */
    private Long professionCode;

    /**
     * 毕业学校编码
     */
    private Long granduteSchoolCode;
}
