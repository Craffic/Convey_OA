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
     * 资源url
     */
    private String url;

    /**
     * 系统菜单
     */
    private String path;

    /**
     * 菜单组件
     */
    private String component;

    /**
     * 菜单名
     */
    private String name;

    /**
     * 菜单图标
     */
    private String iconCls;

    /**
     * 是否激活
     */
    private Integer keepAlive;

    /**
     * 是否需要权限
     */
    private Integer requireAuth;

    /**
     * 父id
     */
    private Long parentId;

    /**
     * 是否可用
     */
    private Integer enabled;

    /**
     * 子菜单
     */
    private List<CvMenu> children;

    /**
     * 资源对应的角色
     */
    List<CvRole> roles;
}
