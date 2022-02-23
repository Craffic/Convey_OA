package com.craffic.convey.jobserver.dao;

import com.craffic.convey.jobserver.model.People;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PeopleMapper {

    List<People> getAllPeoples();

}
