package com.craffic.convey.server.dao;

import com.craffic.convey.server.model.OaDict;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OaDictMapper {

    List<OaDict> queryDictListByItemName(@Param("itemName") String itemName);

    OaDict queryDictByKey(@Param("key") Long key, @Param("itemName") String itemName);

    List<String> queryAllItemNameDist();
}
