package com.craffic.convey.jobapi.req;

import com.craffic.convey.common.request.BaseQuery;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
public class PersonReq extends BaseQuery {

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
     * 查询出生日期范围
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date birthDateStart;

    /**
     * 查询出生日期范围
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date birthDateEnd;

    /**
     * 籍贯
     */
    private Long nativePlaceCode;

    /**
     * 家庭地址
     */
    private String homeProvince;
    private String homeCity;
    private String homeArea;

    /**
     * 工作地址
     */
    private String workProvince;
    private String workCity;
    private String workArea;

    /**
     * 年龄
     */
    private Integer age;
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

    /**
     * 收藏标志
     */
    private Boolean favorite;
}
