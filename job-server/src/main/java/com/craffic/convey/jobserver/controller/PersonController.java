package com.craffic.convey.jobserver.controller;

import com.craffic.convey.common.vo.ListVo;
import com.craffic.convey.jobserver.model.Person;
import com.craffic.convey.jobserver.req.PersonReq;
import com.craffic.convey.jobserver.service.PersonService;
import com.craffic.convey.jobserver.vo.PersonVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.craffic.convey.common.response.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("queryAll")
    public List<Person> queryAllPersonList(){
        return personService.queryAllPersons();
    }

    /**
     * 根据条件查询
     */
    @GetMapping("/query")
    public ResponseBody<ListVo<PersonVo>> queryPersonByCondition(PersonReq personReq){
        ListVo<Person> personListVo = personService.queryUsersByCondition(personReq);
        List<Person> list = personListVo.getList();
        List<PersonVo> personVoList = new ArrayList<>();
        list.stream().forEach(person -> {
            PersonVo personVo = new PersonVo();
            BeanUtils.copyProperties(person, personVo);
            personVoList.add(personVo);
        });
        ListVo<PersonVo> listVo = new ListVo<>(personVoList, personListVo.getTotalNum());
        return ResponseBody.success(listVo);
    }
}