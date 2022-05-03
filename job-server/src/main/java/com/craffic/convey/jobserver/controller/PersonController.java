package com.craffic.convey.jobserver.controller;

import com.craffic.convey.common.vo.ListVo;
import com.craffic.convey.jobapi.req.PersonReq;
import com.craffic.convey.jobapi.vo.PersonVo;
import com.craffic.convey.jobserver.model.Person;
import com.craffic.convey.jobserver.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.craffic.convey.common.response.ResponseBody;

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
    @PostMapping(value = "/query")
    public ResponseBody<ListVo<PersonVo>> queryPersonByCondition(PersonReq personReq){
        ListVo<PersonVo> personListVo = personService.queryUsersByCondition(personReq);
        return ResponseBody.success(personListVo);
    }

            @GetMapping(value = "/query")
    public ResponseBody<PersonVo> queryPersonByIdNo(@RequestParam("idNo") String idNo){
        PersonVo personVo = personService.queryPersonInfo(idNo);
        return ResponseBody.success(personVo);
    }
}