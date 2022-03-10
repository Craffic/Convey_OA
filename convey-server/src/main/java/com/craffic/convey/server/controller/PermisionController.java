package com.craffic.convey.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PermisionController {

    @GetMapping("/db")
    public String dbTest(){
        return "db test";
    }

    @GetMapping("admin")
    public String adminTest(){
        return "admin test";
    }
    @GetMapping("/normal")
    public String normalTest(){
        return "normal test";
    }
}
