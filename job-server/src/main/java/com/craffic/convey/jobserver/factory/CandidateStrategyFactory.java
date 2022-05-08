package com.craffic.convey.jobserver.factory;

import com.craffic.convey.jobserver.model.Person;
import com.craffic.convey.jobserver.strategy.AbstractCandidateFilter;
import com.craffic.convey.jobserver.strategy.impl.CandidateAgeFilter;
import com.craffic.convey.jobserver.strategy.impl.CandidateProfessionFilter;
import com.craffic.convey.jobserver.strategy.impl.CandidateUniversityFilter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 候选人工厂
 * 经过多重策略筛选出符合条件的候选人
 */
@Component
public class CandidateStrategyFactory {



    @Resource(name = "candidateAgeFilter")
    private CandidateAgeFilter candidateAgeFilter;

    @Resource(name = "candidateProfessionFilter")
    private CandidateProfessionFilter candidateProfessionFilter;

    @Resource(name = "candidateUniversityFilter")
    private CandidateUniversityFilter candidateUniversityFilter;

    public List<AbstractCandidateFilter> strategyList = new ArrayList();

    @PostConstruct
    public void init(){
        strategyList.add(candidateAgeFilter);
        strategyList.add(candidateProfessionFilter);
        strategyList.add(candidateUniversityFilter);
    }
}
