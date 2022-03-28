package com.craffic.convey.server.common;


import lombok.Data;
import org.springframework.boot.context.properties.bind.DefaultValue;

@Data
public class BaseQuery extends BaseVO{

    /**
     * 当前页码
     */
    private Integer page = 1;

    /**
     * 每页大小
     */
    private Integer size  =10;

    public Integer pageStartIndex(){
        return size * (page - 1);
    }
}
