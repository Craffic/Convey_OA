package com.craffic.convey.jobserver.req;

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
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date birthDate;

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
    private String homeAddress;

    /**
     * 工作地址
     */
    private String workAddress;

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

    public String getIdcardNo() {
        return idcardNo;
    }

    public void setIdcardNo(String idcardNo) {
        this.idcardNo = idcardNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Long getNativePlaceCode() {
        return nativePlaceCode;
    }

    public void setNativePlaceCode(Long nativePlaceCode) {
        this.nativePlaceCode = nativePlaceCode;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(String workAddress) {
        this.workAddress = workAddress;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Long getProfessionCode() {
        return professionCode;
    }

    public void setProfessionCode(Long professionCode) {
        this.professionCode = professionCode;
    }

    public Long getGranduteSchoolCode() {
        return granduteSchoolCode;
    }

    public void setGranduteSchoolCode(Long granduteSchoolCode) {
        this.granduteSchoolCode = granduteSchoolCode;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
