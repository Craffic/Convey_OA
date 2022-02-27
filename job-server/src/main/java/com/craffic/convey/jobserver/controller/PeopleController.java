package com.craffic.convey.jobserver.controller;

import com.craffic.convey.jobserver.model.People;
import com.craffic.convey.jobserver.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("people")
public class PeopleController {

    @Autowired
    private PeopleService peopleService;

    @GetMapping("queryAll")
    public List<People> queryAllPeopleList(){
        return peopleService.queryAllPeoples();
    }

}