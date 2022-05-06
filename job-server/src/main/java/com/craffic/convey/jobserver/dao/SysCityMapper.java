package com.craffic.convey.jobserver.dao;

import com.craffic.convey.jobapi.vo.SysCityVo;
import com.craffic.convey.jobapi.vo.SysProvinceVo;
import com.craffic.convey.jobserver.model.SysCity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysCityMapper {

    List<SysCity> getCityByProvince(@Param("provinceId") Long provinceId);

}
