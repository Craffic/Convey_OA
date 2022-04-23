package com.craffic.convey.server.vo;

import com.craffic.convey.common.vo.BaseVO;
import lombok.Data;

@Data
public class OaDictVo extends BaseVO {

    private String id;

    private String itemName;

    private Long pKey;

    private String pValue;

    private Long key;

    private String value;

}