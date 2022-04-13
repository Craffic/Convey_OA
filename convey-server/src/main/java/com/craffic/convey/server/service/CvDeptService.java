package com.craffic.convey.server.service;

import com.craffic.convey.server.dao.CvDeptMapper;
import com.craffic.convey.server.model.CvDept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CvDeptService {

    @Autowired
    private CvDeptMapper deptMapper;

    public List<CvDept> queryAllDepartments() {
        return deptMapper.queryAllDeptByParentId(-1);
    }

}
