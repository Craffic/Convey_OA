package com.craffic.convey.server.dao;

import com.craffic.convey.server.model.CvRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CvRoleMapper {

    List<CvRole> getAllRoles();

    List<CvRole> getRolesByUserId(@Param("userId") Long userId);
}