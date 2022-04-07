package com.craffic.convey.jobserver.service.rest;

import com.alibaba.dubbo.config.annotation.Service;
import com.craffic.convey.api.PersonInterface;

@Service
public class PersonRestService implements PersonInterface {
    @Override
    public String sayHello() {
        return "Hello, Job Service";
    }
}