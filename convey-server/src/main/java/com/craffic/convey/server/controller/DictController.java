package com.craffic.convey.server.controller;

import com.craffic.convey.common.response.ResponseBody;
import com.craffic.convey.server.model.OaDict;
import com.craffic.convey.server.service.OaDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/dict")
public class DictController {

    @Autowired
    OaDictService dictService;

    @GetMapping("/item_name/list")
    public ResponseBody<List<OaDict>> queryAllItemNameDist(){
        List<String> itemNameList = dictService.queryAllItemNameDist();
        List<OaDict> dictList = new ArrayList<>();
        for (int i = 0; i < itemNameList.size(); i++) {
            OaDict dict = new OaDict();
            dict.setKey(Long.valueOf(i));
            dict.setItemName(itemNameList.get(i));
            dictList.add(dict);
        }
        return ResponseBody.success(dictList);
    }

    @GetMapping("item_name")
    public ResponseBody<List<OaDict>> queryItemsByName(@RequestParam("item_name") String itemName){
        if (StringUtils.hasText(itemName)) {
            List<OaDict> dictList = dictService.queryDictList(itemName);
            return ResponseBody.success(dictList);
        }
        return ResponseBody.failure("400109", "查询字典表失败");
    }

    @GetMapping("/query/item")
    public ResponseBody<OaDict> queryItemByKey(@RequestParam("key") Long key, @RequestParam("item_name") String itemName){
        if (StringUtils.hasText(itemName) && key != null) {
            OaDict dict = dictService.queryDictByKey(key, itemName);
            return ResponseBody.success(dict);
        }
        return ResponseBody.failure("400109", "查询字典表失败");
    }
}
