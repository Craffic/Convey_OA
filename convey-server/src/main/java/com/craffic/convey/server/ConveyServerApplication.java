package com.craffic.convey.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.craffic.convey.server.dao")
public class ConveyServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConveyServerApplication.class, args);
        System.out.println("Convey-Server启动完成......");
    }

}
