package com.craffic.convey.server.model;

import lombok.Data;

import java.util.List;

@Data
public class CvDept {

    private Integer id;

    private String name;

    private String parentId;

    private String deptPath;

    private Boolean enabled;

    private Boolean isParent;

    private List<CvDept> children;
}
