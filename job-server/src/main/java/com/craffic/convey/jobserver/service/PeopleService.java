package com.craffic.convey.jobserver.service;

import com.craffic.convey.jobserver.dao.PeopleMapper;
import com.craffic.convey.jobserver.model.OaDict;
import com.craffic.convey.jobserver.model.People;
import com.craffic.convey.jobserver.utils.DateUtil;
import com.craffic.convey.jobserver.utils.RandomGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PeopleService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private PeopleMapper peopleMapper;
    @Autowired
    private OaDictService dictService;

    public static final String AREA = "AREA";

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
        Date birthDate = RandomGenerator.randomDate();
        String gender = RandomGenerator.genderGenerator();
        // 获取地区集合
        List<OaDict> areaList = dictService.queryDictByName(AREA);
        int num = RandomGenerator.randomNumFromList(areaList);
        Long nativePlaceCode = areaList.get(num).getKey();
        // 生成身份证号
        String idcardNo = RandomGenerator.idCardNoGenerator(birthDate, nativePlaceCode, gender);
        String name = RandomGenerator.nameGenerator(gender);
        String homeAddress = "增城市石滩镇";
        String workAddress = "天河区";
        int age = DateUtil.calYearDiff(new Date(), birthDate);
        Long professionCode = 200001L;
        Long granduteSchoolCode = 300001L;
        Date createdDate = new Date();
        String createdBy = "system";
        Date updateDate = new Date();
        String updateBy = "system";
        return new People(idcardNo, name, gender, nativePlaceCode, birthDate, homeAddress, workAddress, age,
                          professionCode, granduteSchoolCode, createdDate, createdBy, updateDate, updateBy);

    }
}
