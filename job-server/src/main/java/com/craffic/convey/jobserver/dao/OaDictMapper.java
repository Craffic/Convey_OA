package com.craffic.convey.jobserver.dao;

import com.craffic.convey.jobserver.model.OaDict;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@Mapper
public interface OaDictMapper {

    List<OaDict> getAllDict();

    List<OaDict> getAllDictByName(@Param("itemName") String itemName);

    Integer insertSelective(OaDict dict);

}
