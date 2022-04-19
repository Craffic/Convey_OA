package com.craffic.convey.server.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class OaDict implements Serializable {
    private String id;

    private String itemName;

    private Long pKey;

    private Long key;

    private String value;

    private String comments;

    private Date createdDate;

    private String createdBy;

    private Date updateDate;

    private String updateBy;
}
