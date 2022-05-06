package com.craffic.convey.jobserver.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysProvince implements Serializable {

    private Long provinceId;

    private String provinceName;
}
