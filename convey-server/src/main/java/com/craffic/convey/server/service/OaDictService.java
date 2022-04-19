package com.craffic.convey.server.service;

import com.craffic.convey.server.dao.OaDictMapper;
import com.craffic.convey.server.model.OaDict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OaDictService {

    @Autowired
    OaDictMapper dictMapper;

    public List<OaDict> queryDictList(String itemName){
        return dictMapper.queryDictListByItemName(itemName);
    }

}
