package com.craffic.convey.jobserver.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: Liu Chengyan
 * 社会人员实体类
 */
public class Person implements Serializable {

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

    public Long getNativePlace() {
        return nativePlaceCode;
    }

    public void setNativePlace(Long nativePlaceCode) {
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

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Person() {
    }

    public Person(String idcardNo, String name, String gender, Long nativePlaceCode, Date birthDate,
                  String homeAddress, String workAddress, Integer age, Long professionCode,
                  Long granduteSchoolCode, Date createdDate, String createdBy, Date updateDate, String updateBy) {
        this.idcardNo = idcardNo;
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.nativePlaceCode = nativePlaceCode;
        this.homeAddress = homeAddress;
        this.workAddress = workAddress;
        this.age = age;
        this.professionCode = professionCode;
        this.granduteSchoolCode = granduteSchoolCode;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.updateDate = updateDate;
        this.updateBy = updateBy;
    }
}
