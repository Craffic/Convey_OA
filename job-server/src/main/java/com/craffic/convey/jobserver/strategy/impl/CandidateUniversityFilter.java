package com.craffic.convey.jobserver.strategy.impl;

import com.craffic.convey.common.constant.ConveyConstant;
import com.craffic.convey.jobserver.model.Person;
import com.craffic.convey.jobserver.strategy.AbstractCandidateFilter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier(value = "candidateUniversityFilter")
public class CandidateUniversityFilter implements AbstractCandidateFilter {

    @Override
    public boolean filter(Person person) {
        if (person.getGranduteSchoolCode() == null) {
            return false;
        }
        return true;
    }
}
