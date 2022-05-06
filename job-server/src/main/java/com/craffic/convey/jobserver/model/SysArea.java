package com.craffic.convey.jobserver.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysArea implements Serializable {

    private Long areaId;

    private String areaName;

    private Long cityId;
}
