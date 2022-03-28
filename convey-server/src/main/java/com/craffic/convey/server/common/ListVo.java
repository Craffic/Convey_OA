package com.craffic.convey.server.common;

import lombok.Data;

import java.util.List;

@Data
public class ListVo<T> extends BaseVO {

    private static final long serialVersionUID = -4213992025937477654L;

    /*数据集合*/
    private List<T> list;

    /*结果集总数*/
    private Integer totalNum;

    public ListVo(List<T> list, Integer totalNum) {
        this.list = list;
        this.totalNum = totalNum;
    }
}
