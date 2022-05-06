package com.craffic.convey.jobserver.service;

import com.craffic.convey.common.utils.DateUtil;
import com.craffic.convey.common.vo.ListVo;
import com.craffic.convey.jobapi.req.PersonReq;
import com.craffic.convey.jobapi.vo.PersonVo;
import com.craffic.convey.jobserver.dao.PersonMapper;
import com.craffic.convey.jobserver.model.OaDict;
import com.craffic.convey.jobserver.model.Person;
import com.craffic.convey.jobserver.utils.JsonUtil;
import com.craffic.convey.jobserver.utils.RandomGenerator;
import com.craffic.convey.jobserver.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PersonService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PersonMapper personMapper;
    @Autowired
    private RedisUtil redisUtil;

    public static final String PROVINCE = "PROVINCE";
    public static final String CITY = "CITY";
    public static final String AREA = "AREA";
    public static final String PROFESSIONAL = "PROFESSIONAL";
    public static final String UNIVERSITY = "UNIVERSITY";
    public static final String SYSTEM = "system";

    /**
     * 查询所有人员列表
     * @return
     */
    public List<Person> queryAllPersons(){
        return personMapper.getAllPersons();
    }

    /**
     * 插入person
     */
    public int insertPerson(Person person){
        return personMapper.insertSelective(person);
    }

    public Person genPersonInfo(){
        Date birthDate = RandomGenerator.randomDate();
        String gender = RandomGenerator.genderGenerator();
        // 获取地区集合
        List<OaDict> areaList = JsonUtil.jsonToList(redisUtil.queryByKey(AREA), OaDict.class);
        int num = RandomGenerator.randomNumFromList(areaList);
        Long nativePlaceCode = areaList.get(num).getKey();
        // 生成身份证号
        String idCardNo = RandomGenerator.idCardNoGenerator(birthDate, nativePlaceCode, gender);
        String name = RandomGenerator.nameGenerator(gender);
        int age = DateUtil.calYearDiff(new Date(), birthDate);
        // 职业
        List<OaDict> professionalList = JsonUtil.jsonToList(redisUtil.queryByKey(PROFESSIONAL), OaDict.class);
        int num1 = RandomGenerator.randomNumFromList(professionalList);
        Long professionCode = professionalList.get(num1).getKey();

        List<OaDict> universityList = JsonUtil.jsonToList(redisUtil.queryByKey(UNIVERSITY), OaDict.class);
        int num2 = RandomGenerator.randomNumFromList(universityList);
        Long granduteSchoolCode = universityList.get(num2).getKey();
        Date createdDate = new Date();
        String createdBy = SYSTEM;
        Date updateDate = new Date();
        String updateBy = SYSTEM;
        // 户籍省市区
        Map<String, Long> homeAddressMap = genAddress();
        Long homeProvince = homeAddressMap.get(PROVINCE);
        Long homeCity = homeAddressMap.get(CITY);
        Long homeArea = homeAddressMap.get(AREA);
        Map<String, Long> workAddressMap = genAddress();
        Long workProvince = workAddressMap.get(PROVINCE);
        Long workCity = workAddressMap.get(CITY);
        Long workArea = workAddressMap.get(AREA);

        return new Person(idCardNo, name, gender, nativePlaceCode, birthDate,
                          homeProvince, homeCity, homeArea, workProvince, workCity, workArea, age,
                          professionCode, granduteSchoolCode, createdDate, createdBy, updateDate, updateBy);

    }

    /**
     * 生成地址
     * @return
     */
    public Map<String, Long> genAddress(){
        Map<String, Long> resultMap = new HashMap<>();
        List<OaDict> provinceList = JsonUtil.jsonToList(redisUtil.queryByKey(PROVINCE), OaDict.class);
        int provinceNum = RandomGenerator.randomNumFromList(provinceList);
        OaDict provinceDict = provinceList.get(provinceNum);

        List<OaDict> allCityList = JsonUtil.jsonToList(redisUtil.queryByKey(CITY), OaDict.class);
        List<OaDict> cityList = allCityList.stream().filter(city -> city.getpKey().equals(provinceDict.getKey())).collect(Collectors.toList());
        int cityNum = RandomGenerator.randomNumFromList(cityList);
        OaDict cityDict = cityList.get(cityNum);

        List<OaDict> allAreaList = JsonUtil.jsonToList(redisUtil.queryByKey(AREA), OaDict.class);
        List<OaDict> areaList = allAreaList.stream().filter(area -> area.getpKey().equals(cityDict.getKey())).collect(Collectors.toList());
        int areaNum = RandomGenerator.randomNumFromList(areaList);
        OaDict areaDict = areaList.get(areaNum);

        StringBuffer address = new StringBuffer();
        resultMap.put(PROVINCE, provinceDict.getKey());
        resultMap.put(CITY, cityDict.getKey());
        resultMap.put(AREA, areaDict.getKey());
        return resultMap;
    }


    /**
     * 根据条件查询用户列表
     */
    public ListVo<PersonVo> queryUsersByCondition(PersonReq personReq){
        personReq.setStartRecord(personReq.pageStartIndex());
        personReq.setEndRecord(personReq.getPage() * personReq.getSize());
        Long total = personMapper.queryTotalNum(personReq);
        if (total <= 0) {
            return new ListVo<>(null, 0);
        }
        List<Person> userList = personMapper.queryPersonsByCondition(personReq);
        List<PersonVo> personVos = new ArrayList<>();
        userList.stream().forEach(user -> {
                PersonVo personVo = new PersonVo();
                BeanUtils.copyProperties(user, personVo);
                personVos.add(personVo);
            });
        return new ListVo(personVos, total.intValue());
    }

    /**
     * 根据身份证号查询个人信息
     * @param idNo
     * @return
     */
     public PersonVo queryPersonInfo(String idNo) {
         return personMapper.queryPersonInfo(idNo);
    }
}
