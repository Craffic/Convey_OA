package com.craffic.convey.server.controller;

import com.craffic.convey.common.response.ResponseBody;
import com.craffic.convey.server.model.CvDept;
import com.craffic.convey.server.service.CvDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 添加
     * @param dept
     * @return
     */
    @PostMapping(value = "add")
    public ResponseBody<CvDept> addDept(@RequestBody CvDept dept){
        CvDept respDept = deptService.addDept(dept);
        if (respDept != null){
            return ResponseBody.success(respDept);
        }
        return ResponseBody.failure("400107", "新增部门失败");
    }

    /**
     * 删除部门
     * @param id
     * @return
     */
    @DeleteMapping(value = "/delete/{id}")
    public ResponseBody<String> deleteDept(@PathVariable Integer id){
        Boolean flag = deptService.deleteDept(id);
        if (flag) {
            return ResponseBody.success("删除部门【"+ id +"】成功！");
        }
        return ResponseBody.failure("400108", "删除部门失败");
    }
}
