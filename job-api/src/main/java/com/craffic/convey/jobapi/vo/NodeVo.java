package com.craffic.convey.jobapi.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class NodeVo implements Serializable {

    private String value;

    private String label;

    private List<NodeVo> children;

}
