package com.craffic.convey.jobserver.service;

import com.craffic.convey.common.vo.ListVo;
import com.craffic.convey.jobapi.req.PersonReq;
import com.craffic.convey.jobapi.vo.PersonVo;
import com.craffic.convey.jobserver.dao.PersonMapper;
import com.craffic.convey.jobserver.factory.PersonFactory;
import com.craffic.convey.jobserver.model.OaDict;
import com.craffic.convey.jobserver.model.Person;
import com.craffic.convey.jobserver.utils.JsonUtil;
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
    private PersonFactory personFactory;
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
        return personFactory.generatePersonInstance();
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
            // 省
            String strWorkProvince = transferEnum(PROVINCE, personVo.getWorkProvince());
            String strHomeProvince = transferEnum(PROVINCE, personVo.getHomeProvince());
            // 市
            String strHomeCity = transferEnum(CITY, personVo.getHomeCity());
            String strWorkCity =transferEnum(CITY, personVo.getWorkCity());
            // 区
            String strHomeArea = transferEnum(AREA, personVo.getHomeArea());
            String strWorkArea = transferEnum(AREA, personVo.getWorkArea());
            StringBuffer homeAddress = new StringBuffer();
            StringBuffer workAddress = new StringBuffer();
            homeAddress.append(strHomeProvince).append(strHomeCity).append(strHomeArea);
            workAddress.append(strWorkProvince).append(strWorkCity).append(strWorkArea);
            personVo.setHomeAddress(homeAddress.toString());
            personVo.setWorkAddress(workAddress.toString());
            // 籍贯
            personVo.setNativePlaceDesc(transferEnum(AREA, personVo.getNativePlaceCode()));
            // 专业
            personVo.setProfessionDesc(transferEnum(PROFESSIONAL, personVo.getProfessionCode()));
            // 学校
            personVo.setGranduteSchoolDesc(transferEnum(UNIVERSITY, personVo.getGranduteSchoolCode()));
            personVos.add(personVo);
        });
        return new ListVo(personVos, total.intValue());
    }

    /**
     * 转换枚举
     */
    public String transferEnum(String type, Long key){
        List<OaDict> dictList = JsonUtil.jsonToList(redisUtil.queryByKey(type), OaDict.class);
        List<OaDict> resultList = dictList.stream().filter(item -> item.getKey().equals(key)).collect(Collectors.toList());
        return resultList.get(0).getValue();
    }

    /**
     * 根据身份证号查询个人信息
     * @param idNo
     * @return
     */
     public PersonVo queryPersonInfo(String idNo) {
         return personMapper.queryPersonInfo(idNo);
    }

    /**
     * 更新个人信息
     */
    public int updatePersonInfo(PersonReq personReq) {
        return personMapper.updatePersonInfo(personReq);
    }
}
