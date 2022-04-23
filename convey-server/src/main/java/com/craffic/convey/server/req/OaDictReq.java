package com.craffic.convey.server.req;

import com.craffic.convey.common.request.BaseQuery;
import lombok.Data;

@Data
public class OaDictReq extends BaseQuery {

    private String itemName;

    private Long pKey;

    private Long key;

    private String value;

    private String comments;
}
