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

    public static final String PROVINCE = "PROVINCE";

    @Autowired
    private PeopleMapper peopleMapper;
    @Autowired
    private OaDictService dictService;

    public static final String AREA = "AREA";
    public static final String PROFESSIONAL = "PROFESSIONAL";
    public static final String UNIVERSITY = "UNIVERSITY";

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
        int age = DateUtil.calYearDiff(new Date(), birthDate);
        // 职业
        List<OaDict> professionalList = dictService.queryDictByName(PROFESSIONAL);
        int num1 = RandomGenerator.randomNumFromList(professionalList);
        Long professionCode = professionalList.get(num1).getKey();

        List<OaDict> universityList = dictService.queryDictByName(UNIVERSITY);
        int num2 = RandomGenerator.randomNumFromList(universityList);
        Long granduteSchoolCode = universityList.get(num2).getKey();
        Date createdDate = new Date();
        String createdBy = "system";
        Date updateDate = new Date();
        String updateBy = "system";
        return new People(idcardNo, name, gender, nativePlaceCode, birthDate, genAddress(), genAddress(), age,
                          professionCode, granduteSchoolCode, createdDate, createdBy, updateDate, updateBy);

    }

    /**
     * 生成地址
     * @return
     */
    public String genAddress(){
        List<OaDict> provinceList = dictService.queryDictByName(PROVINCE);
        int provinceNum = RandomGenerator.randomNumFromList(provinceList);
        OaDict provinceDict = provinceList.get(provinceNum);

        List<OaDict> cityList = dictService.queryDictByPkey(provinceDict.getKey());
        int cityNum = RandomGenerator.randomNumFromList(cityList);
        OaDict cityDict = cityList.get(cityNum);

        List<OaDict> areaList = dictService.queryDictByPkey(cityDict.getKey());
        int areaNum = RandomGenerator.randomNumFromList(areaList);
        OaDict areaDict = null;
        areaDict = areaList.get(areaNum);

        StringBuffer address = new StringBuffer();
        return address.append(provinceDict.getValue()).append(cityDict.getValue()).append(areaDict.getValue()).toString();
    }
}
