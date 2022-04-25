package com.craffic.convey.server.dao;

import com.craffic.convey.server.model.OaDict;
import com.craffic.convey.server.req.OaDictReq;
import com.craffic.convey.server.vo.OaDictVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OaDictMapper {

    List<OaDict> queryDictListByItemName(@Param("itemName") String itemName);

    OaDict queryDictByKey(@Param("key") Long key, @Param("itemName") String itemName);

    List<String> queryAllItemNameDist();

    List<OaDict> queryDistPNodeByItemName(@Param("itemName") String itemName);

    Long queryTotalNum(@Param("dict") OaDictReq req);

    List<OaDictVo> queryByPage(@Param("dict") OaDictReq req);

    Integer addDict(OaDictReq req);
}
