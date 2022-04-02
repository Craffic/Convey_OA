package com.craffic.convey.jobserver.dao;

import com.craffic.convey.jobserver.model.Person;
import com.craffic.convey.jobserver.req.PersonReq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PersonMapper {

    List<Person> getAllPersons();

    Integer insertSelective(Person person);

    List<Person> queryPersonsByCondition(@Param("person") PersonReq person);

    Long queryTotalNum(@Param("person") PersonReq person);
}
