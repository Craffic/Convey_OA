package com.craffic.convey.jobserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan(value = "com.craffic.convey.jobserver.dao")
@EnableScheduling
public class JobServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(JobServerApplication.class, args);
        System.out.println("ConveyOA jobServer模块启动完成！");
    }

}
