package com.craffic.convey.server.controller;

import com.craffic.convey.common.response.RespBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @GetMapping("/login")
    public RespBean login(){
        return RespBean.error("未登录，请先登录....");
    }
}