package com.craffic.convey.jobserver.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Scheduled(cron="0 0/10 * * * ?")
    public void statusCheck() {
        logger.info("每分钟执行一次。开始……");
        //statusTask.healthCheck();
        logger.info("每分钟执行一次。结束。");
    }

    @Scheduled(fixedRate=2000000)
    public void testTasks() {
        logger.info("每200秒执行一次。开始……");
        //statusTask.healthCheck();
        logger.info("每200秒执行一次。结束。");
    }


}
