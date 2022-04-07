package com.craffic.convey.server.controller.rest;

import com.alibaba.dubbo.config.annotation.Reference;
import com.craffic.convey.api.PersonInterface;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonRestController {

    @Reference
    PersonInterface personInterface;

    @GetMapping("/hello")
    public String sayHello(){
        return personInterface.sayHello();
    }

}
