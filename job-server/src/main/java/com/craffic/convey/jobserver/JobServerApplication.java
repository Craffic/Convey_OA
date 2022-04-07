package com.craffic.convey.jobserver;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan(value = "com.craffic.convey.jobserver.dao")
@EnableScheduling
@EnableCaching
@EnableDubbo
public class JobServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(JobServerApplication.class, args);
        System.out.println("ConveyOA jobServer模块启动完成！");
    }

}
