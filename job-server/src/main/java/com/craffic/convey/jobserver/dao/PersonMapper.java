package com.craffic.convey.jobserver.dao;

import com.craffic.convey.jobapi.req.PersonReq;
import com.craffic.convey.jobapi.vo.PersonVo;
import com.craffic.convey.jobserver.model.Person;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PersonMapper {

    List<Person> getAllPersons();

    Integer insertSelective(Person person);

    List<Person> queryPersonsByCondition(@Param("person") PersonReq person);

    Long queryTotalNum(@Param("person") PersonReq person);

    PersonVo queryPersonInfo(@Param("idNo") String idNo);

    int updatePersonInfo(PersonReq personReq);
}
