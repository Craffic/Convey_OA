package com.craffic.convey.jobserver.strategy.impl;

import com.craffic.convey.common.constant.ConveyConstant;
import com.craffic.convey.jobserver.model.Person;
import com.craffic.convey.jobserver.strategy.AbstractCandidateFilter;
import com.google.common.collect.ImmutableSet;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Qualifier(value = "candidateProfessionFilter")
public class CandidateProfessionFilter implements AbstractCandidateFilter {

    @Override
    public boolean filter(Person person) {
        Long professionCode = person.getProfessionCode();
        if (professionCode == null) {
            return false;
        }
        if (!ConveyConstant.qualityProfession.contains(professionCode)) {
            return false;
        }
        return true;
    }
}
