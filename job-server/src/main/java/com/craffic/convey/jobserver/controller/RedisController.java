package com.craffic.convey.jobserver.controller;

import com.alibaba.fastjson.JSONObject;
import com.craffic.convey.jobserver.model.OaDict;
import com.craffic.convey.jobserver.service.OaDictService;
import com.craffic.convey.jobserver.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    OaDictService dictService;
    @Autowired
    RedisUtil redisUtil;

    public static final String KEY_UNIVERSITY = "UNIVERSITY";

    @GetMapping("/set")
    public String setRedisData(@RequestParam("key") String key){
        List<OaDict> universityList = dictService.queryDictByName(KEY_UNIVERSITY);

        String jsonStr = JSONObject.toJSONString(universityList);
        System.out.println(jsonStr);

        ArrayList<OaDict> list = (ArrayList<OaDict>) JSONObject.parseObject(jsonStr, List.class);
        System.out.println(list);


        return "university";
    }


}
