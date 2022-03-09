package com.craffic.convey.server.model;

import lombok.Data;

import java.util.Date;

@Data
public class CvUserRole {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 角色Id
     */
    private String roleId;

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
}