package com.craffic.convey.jobserver.service;

import com.craffic.convey.jobserver.dao.PeopleMapper;
import com.craffic.convey.jobserver.model.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeopleService {

    @Autowired
    private PeopleMapper peopleMapper;

    public List<People> queryAllPeoples(){
        return peopleMapper.getAllPeoples();
    }

}
