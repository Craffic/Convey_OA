package com.craffic.convey.jobserver.scheduler;

import com.craffic.convey.jobserver.model.People;
import com.craffic.convey.jobserver.service.PeopleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 社会人员生成器
 */
@Component
public class GeneratePeopleScheduler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PeopleService peopleService;

    @Scheduled(fixedRate=20000)
    public void generatePeopleJob() {
        People people = peopleService.genPeopleInfo();
        peopleService.insertPeople(people);
        logger.info("生成一名社会人员.......");
    }
}
