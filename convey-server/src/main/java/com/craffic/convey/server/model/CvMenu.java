package com.craffic.convey.server.model;

import lombok.Data;

import java.util.List;

@Data
public class CvMenu {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 资源路径
     */
    private String pattern;

    /**
     * 资源对应的角色
     */
    List<CvRole> roles;
}
