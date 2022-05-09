package com.craffic.convey.jobserver.scheduler;

import com.craffic.convey.jobserver.factory.CandidateStrategyFactory;
import com.craffic.convey.jobserver.model.Person;
import com.craffic.convey.jobserver.service.PersonService;
import com.craffic.convey.jobserver.strategy.AbstractCandidateFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;

/**
 * 挑选面试候选人定时器
 */
@Component
public class QualifierScheduler {

    @Autowired
    private PersonService personService;

    @Autowired
    private CandidateStrategyFactory candidateStrategyFactory;

//    @Scheduled(fixedRate=2000)
    public void Qualify(){

        // 选择所有未检查的人员名单
        List<Person> personList = personService.queryAllPersons();
        if (CollectionUtils.isEmpty(personList)) {
            return;
        }
        for (Person person : personList) {
            List<AbstractCandidateFilter> strategyList = candidateStrategyFactory.strategyList;
            for (AbstractCandidateFilter strategy : strategyList) {
                if (!strategy.filter(person)) {
                    continue;
                }
            }
            System.out.println(person.toString());
        }

    }

}
