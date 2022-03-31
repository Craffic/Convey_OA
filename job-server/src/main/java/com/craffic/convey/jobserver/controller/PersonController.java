package com.craffic.convey.jobserver.controller;

import com.craffic.convey.jobserver.model.Person;
import com.craffic.convey.jobserver.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("queryAll")
    public List<Person> queryAllPersonList(){
        return personService.queryAllPersons();
    }

}