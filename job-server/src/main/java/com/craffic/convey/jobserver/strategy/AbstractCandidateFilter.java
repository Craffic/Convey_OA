package com.craffic.convey.jobserver.strategy;

import com.craffic.convey.jobserver.model.Person;

public interface AbstractCandidateFilter {
    boolean filter(Person person);
}
