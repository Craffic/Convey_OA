package com.craffic.convey.jobserver.controller;

import com.craffic.convey.jobserver.model.OaDict;
import com.craffic.convey.jobserver.service.OaDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dict")
public class OaDictController {

    @Autowired
    private OaDictService dictService;

    @GetMapping("/queryAll")
    public List<OaDict> queryAll(){
        return dictService.queryAllDict();
    }

    @GetMapping("/query/{item_name}")
    public List<OaDict> queryByName(@PathVariable("item_name") String item_name){
        return dictService.queryDictByName(item_name);
    }

}
