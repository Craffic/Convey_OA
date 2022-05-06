package com.craffic.convey.jobserver.dao;

import com.craffic.convey.jobapi.vo.SysProvinceVo;
import com.craffic.convey.jobserver.model.Person;
import com.craffic.convey.jobserver.model.SysProvince;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysProvinceMapper {

    List<SysProvince> getAllProvinceList();

}
