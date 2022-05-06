package com.craffic.convey.server.controller.social;

import com.alibaba.dubbo.config.annotation.Reference;
import com.craffic.convey.common.response.ResponseBody;
import com.craffic.convey.jobapi.api.CascaderDataInterface;
import com.craffic.convey.jobapi.vo.NodeVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RegionRestController {

    @Reference
    private CascaderDataInterface regionInfoRestService;

    @GetMapping(value = "query/region")
    public ResponseBody<List<NodeVo>> queryRegionData() {
        List<NodeVo> nodeVos = regionInfoRestService.queryRegionCascaderData();
        return ResponseBody.success(nodeVos);
    }


}
