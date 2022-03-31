package com.craffic.convey.jobserver.dao;

import com.craffic.convey.jobserver.model.Person;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PersonMapper {

    List<Person> getAllPersons();

    Integer insertSelective(Person person);

}
