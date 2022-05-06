package com.craffic.convey.jobserver.dao;

import com.craffic.convey.jobapi.vo.SysProvinceVo;
import com.craffic.convey.jobserver.model.SysArea;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysAreaMapper {

    List<SysArea> getAreasByCity(@Param("cityId") Long cityId);

}
