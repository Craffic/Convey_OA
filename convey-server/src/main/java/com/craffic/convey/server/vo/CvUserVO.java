package com.craffic.convey.server.vo;

import com.craffic.convey.server.model.CvUser;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
public class CvUserVO extends CvUser {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 工号
     */
    private Long workId;

    /**
     * 系统账号
     */
    private String acct;

    /**
     * 中文姓名
     */
    private String nameZh;

    /**
     * 密码
     */
    @JsonIgnore
    private String password;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 邮件
     */
    private String email;

    /**
     * 头像rul
     */
    private String iconUrl;

    /**
     * 职位id
     */
    private Long posId;

    /**
     * 所属部门id
     */
    private Long dptId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 入职日期
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date beginDate;

    /**
     * 转正日期
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date convertDate;

    /**
     * 在职状态
     */
    private Integer workStat;

    /**
     * 离职日期
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date leaveDate;

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

    /**
     * 更新人
     */
    private String updateBy;

}
