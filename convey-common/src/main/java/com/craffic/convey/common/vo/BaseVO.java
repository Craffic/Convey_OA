package com.craffic.convey.common.vo;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.io.Serializable;

@Data
public class BaseVO implements Serializable {

    private static final long serialVersionUID = -4213992025937477652L;

    @Override
    public String toString() {
        return JSONObject.toJSON(this).toString();
    }
}
