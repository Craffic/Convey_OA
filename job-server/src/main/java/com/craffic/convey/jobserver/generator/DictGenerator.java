package com.craffic.convey.jobserver.generator;

import com.craffic.convey.common.constant.ConveyConstant;
import com.craffic.convey.jobserver.model.OaDict;
import com.craffic.convey.jobserver.utils.JsonUtil;
import com.craffic.convey.jobserver.utils.RandomNumUtil;
import com.craffic.convey.jobserver.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 省市区生成器
 */
@Component
public class DictGenerator {

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 字典生成器
     * @param dictType
     * @return
     */
    public Long dictGenerator(String dictType){
        List<OaDict> list = JsonUtil.jsonToList(redisUtil.queryByKey(dictType), OaDict.class);
        // todo 判断areaList为空则抛异常
        Integer num = RandomNumUtil.randomNumFormList(list);
        return list.get(num).getKey();
    }

}
