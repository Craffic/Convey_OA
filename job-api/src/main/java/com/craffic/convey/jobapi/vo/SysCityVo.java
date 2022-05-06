package com.craffic.convey.jobapi.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysCityVo implements Serializable {

    private Long cityId;

    private String cityName;

    private Long provinceId;
}
