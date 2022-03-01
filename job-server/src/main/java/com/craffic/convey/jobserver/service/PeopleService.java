package com.craffic.convey.jobserver.service;

import com.craffic.convey.jobserver.dao.PeopleMapper;
import com.craffic.convey.jobserver.model.People;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class PeopleService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private PeopleMapper peopleMapper;

    /**
     * 查询所有人员列表
     * @return
     */
    public List<People> queryAllPeoples(){
        return peopleMapper.getAllPeoples();
    }

    /**
     * 插入people
     */
    public int insertPeople(People people){
        return peopleMapper.insertSelective(people);
    }

    public People genPeopleInfo(){

        String idcardNo = UUID.randomUUID().toString().substring(0, 17);
        String name = UUID.randomUUID().toString().substring(0, 6);
        String gender = "F";
        Long nativePlaceCode = 100001L;
        String homeAddress = "增城市石滩镇";
        String workAddress = "天河区";
        int age = 30;
        Long professionCode = 200001L;
        Long granduteSchoolCode = 300001L;
        Date createdDate = new Date();
        String createdBy = "system";
        Date updateDate = new Date();
        String updateBy = "system";
        return new People(idcardNo, name, gender, nativePlaceCode, RandomGenerator.randomDate(), homeAddress, workAddress, age,
                          professionCode, granduteSchoolCode, createdDate, createdBy, updateDate, updateBy);

    }
}
