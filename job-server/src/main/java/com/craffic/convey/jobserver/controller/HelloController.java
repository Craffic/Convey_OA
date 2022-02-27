package com.craffic.convey.jobserver.controller;

import com.craffic.convey.jobserver.service.RandomGenerator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/test")
    public String test(){
        return "hello ConveyOA job server!";
    }

    @GetMapping("random/year")
    public Date getRandomYear(){
        return RandomGenerator.randomDate();
    }
}
