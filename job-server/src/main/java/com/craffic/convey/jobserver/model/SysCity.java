package com.craffic.convey.jobserver.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysCity implements Serializable {

    private Long provinceId;

    private Long cityId;

    private String cityName;
}
