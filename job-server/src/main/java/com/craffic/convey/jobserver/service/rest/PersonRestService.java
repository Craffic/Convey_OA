package com.craffic.convey.jobserver.service.rest;

import com.alibaba.dubbo.config.annotation.Service;
import com.craffic.convey.common.response.ResponseBody;
import com.craffic.convey.common.vo.ListVo;
import com.craffic.convey.jobapi.api.PersonInterface;
import com.craffic.convey.jobapi.req.PersonReq;
import com.craffic.convey.jobapi.vo.PersonVo;
import com.craffic.convey.jobserver.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class PersonRestService implements PersonInterface {

    @Autowired
    private PersonService personService;

    @Override
    public String sayHello() {
        return "Hello, Job Service";
    }
    @Override
    public ResponseBody<ListVo<PersonVo>> queryPersonsByPage(PersonReq personReq) {
        ListVo<PersonVo> personListVo = personService.queryUsersByCondition(personReq);
        return ResponseBody.success(personListVo);
    }

    @Override
    public ResponseBody<PersonVo> queryPersonInfo(String idCardNo) {
        PersonVo personVo = personService.queryPersonInfo(idCardNo);
        return ResponseBody.success(personVo);
    }
}