package com.craffic.convey.server.controller;

import com.craffic.convey.common.response.ResponseBody;
import com.craffic.convey.server.model.CvDept;
import com.craffic.convey.server.service.CvDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/dept")
public class CvDeptController {

    @Autowired
    private CvDeptService deptService;

    @GetMapping(value = "/root")
    public ResponseBody<List<CvDept>> queryRootDeptWithChildren(){
        List<CvDept> deptList = deptService.queryAllDepartments();
        return ResponseBody.success(deptList);
    }

}
