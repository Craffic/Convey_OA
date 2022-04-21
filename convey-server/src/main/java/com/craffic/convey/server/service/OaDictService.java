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

    /**
     * 根据key和item_name查询具体的字典项
     */
    public OaDict queryDictByKey(Long key, String itemName){
        return dictMapper.queryDictByKey(key, itemName);
    }

    /**
     * 查询出所有字典项列表
     * @return
     */
    public List<String> queryAllItemNameDist() {
        return dictMapper.queryAllItemNameDist();
    }
}
