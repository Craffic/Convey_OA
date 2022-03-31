package com.craffic.convey.jobserver.utils;

import com.alibaba.fastjson.JSONArray;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JsonUtil {

    /**
     * json转换list<T>
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> jsonToList(String json, Class<T> clazz){
        List<T> list = JSONArray.parseArray(json, clazz);
        return list;
    }

}
