package com.craffic.convey.jobserver.strategy.impl;

import com.craffic.convey.jobserver.model.Person;
import com.craffic.convey.jobserver.strategy.AbstractCandidateFilter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier(value = "candidateAgeFilter")
public class CandidateAgeFilter implements AbstractCandidateFilter {

    @Override
    public boolean filter(Person person) {
        if (person.getAge() == null) {
            return false;
        }
        if (person.getAge() < 24 || person.getAge() > 40) {
            return false;
        }
        return true;
    }
}
