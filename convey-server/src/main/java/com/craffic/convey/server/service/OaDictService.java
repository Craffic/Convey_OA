package com.craffic.convey.server.service;

import com.craffic.convey.common.vo.ListVo;
import com.craffic.convey.server.dao.OaDictMapper;
import com.craffic.convey.server.model.OaDict;
import com.craffic.convey.server.req.OaDictReq;
import com.craffic.convey.server.vo.OaDictVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    /**
     * 根据item_name查询父节点列表
     */
    public List<OaDict> queryDistPNodeByItemName(String itemName) {
        return dictMapper.queryDistPNodeByItemName(itemName);
    }

    /**
     * 分页查询字典值
     */
    public ListVo<OaDictVo> queryDictByPage(OaDictReq req){
        req.setStartRecord(req.pageStartIndex());
        req.setEndRecord(req.getPage() * req.getSize());
        Long num = dictMapper.queryTotalNum(req);
        if (num <= 0) {
            return new ListVo<>(new ArrayList(), 0);
        }
        List<OaDictVo> dictList = dictMapper.queryByPage(req);
        return new ListVo(dictList, num.intValue());
    }
}
