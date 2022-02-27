package com.craffic.convey.jobserver.service;

import com.craffic.convey.jobserver.dao.OaDictMapper;
import com.craffic.convey.jobserver.model.OaDict;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OaDictService {

    @Autowired
    private OaDictMapper oaDictMapper;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 查询所有人员列表
     * @return
     */

    @Cacheable(cacheNames = "all_dict")
    public List<OaDict> queryAllDict(){
        logger.info("查询所有字典值......");
        return oaDictMapper.getAllDict();
    }

    @Cacheable(cacheNames = "dict", key="#itemName")
    public List<OaDict> queryDictByName(String itemName){
        logger.info("查询字典：{}", itemName);
        return oaDictMapper.getAllDictByName(itemName);
    }

    /**
     * 插入people
     */
    public int insertPeople(OaDict dict){
        return oaDictMapper.insertSelective(dict);
    }

}
