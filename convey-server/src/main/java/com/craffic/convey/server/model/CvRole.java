package com.craffic.convey.server.model;

import lombok.Data;
import java.util.Date;

@Data
public class CvRole {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 角色
     */
    private String role;

    /**
     * 角色中文名称
     */
    private String roleName;

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