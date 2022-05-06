package com.craffic.convey.jobapi.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysAreaVo implements Serializable {

    private Long areaId;

    private String areaName;

    private Long cityId;

}
