package com.craffic.convey.common.request;


import com.craffic.convey.common.vo.BaseVO;
import lombok.Data;

@Data
public class BaseQuery extends BaseVO {

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

    private Integer startRecord;

    private Integer endRecord;
}
