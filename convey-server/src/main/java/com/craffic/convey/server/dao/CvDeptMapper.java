package com.craffic.convey.server.dao;

import com.craffic.convey.server.model.CvDept;
import com.craffic.convey.server.model.CvMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CvDeptMapper {

    CvDept queryNodeById(@Param("id") Integer id);

    List<CvDept> queryAllDeptByParentId(@Param("parentId") Integer parentId);

    Integer addDept(CvDept dept);

    Integer updateDept(CvDept dept);

    Integer selectMaxId();

    Integer deleteByList(List<Integer> deptIdList);
}