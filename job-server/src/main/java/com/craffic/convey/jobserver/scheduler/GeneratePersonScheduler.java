package com.craffic.convey.jobserver.scheduler;

import com.craffic.convey.jobserver.factory.PersonFactory;
import com.craffic.convey.jobserver.model.Person;
import com.craffic.convey.jobserver.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 社会人员生成器
 */
@Component
public class GeneratePersonScheduler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PersonService personService;

    @Scheduled(fixedRate=20000)
    public void generatePersonJob() {
        Person person = personService.genPersonInfo();
        personService.insertPerson(person);
        logger.info("生成一名社会人员.......");
    }
}
