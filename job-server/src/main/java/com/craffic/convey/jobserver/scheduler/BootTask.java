package com.craffic.convey.jobserver.scheduler;

import com.alibaba.fastjson.JSONArray;
import com.craffic.convey.jobserver.model.OaDict;
import com.craffic.convey.jobserver.service.OaDictService;
import com.craffic.convey.jobserver.utils.JsonUtil;
import com.craffic.convey.jobserver.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BootTask implements CommandLineRunner {

    @Autowired
    OaDictService dictService;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    JsonUtil jsonUtil;

    @Override
    public void run(String... args) throws Exception {
        // 先查询所有字典项列表
        List<String> itemList = dictService.queryAllDictItems();
        if (CollectionUtils.isEmpty(itemList)) {
            return;
        }
        List<OaDict> allDictList = dictService.queryAllDict();
        for (String itemName : itemList) {
            // 对每一项作为key去redis查询是否有对应的缓存
            if (redisUtil.queryIsLocked(itemName)) {
                // 如果redis里还有缓存，则跳过
                continue;
            }
            List<OaDict> tempList = allDictList.stream().filter(item -> itemName.equals(item.getItemName())).collect(Collectors.toList());
            // list转json
            String jsonList = JSONArray.toJSONString(tempList);
            boolean flag = redisUtil.insertData(itemName, jsonList, 10080L);
            if (!flag) {
                // 失败重连一次
                boolean reconnFlag = redisUtil.insertData(itemName, jsonList, 10080L);
                if (!reconnFlag){
                    throw new Exception("插入字典项失败：" + itemName);
                }
            }

        }


    }
}
