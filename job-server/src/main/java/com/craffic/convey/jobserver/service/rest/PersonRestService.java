package com.craffic.convey.jobserver.service.rest;

import com.alibaba.dubbo.config.annotation.Service;
import com.craffic.convey.common.response.ResponseBody;
import com.craffic.convey.common.vo.ListVo;
import com.craffic.convey.jobapi.api.PersonInterface;
import com.craffic.convey.jobapi.req.PersonReq;
import com.craffic.convey.jobapi.vo.PersonVo;
import com.craffic.convey.jobserver.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.annotation.Retention;

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

    @Override
    public ResponseBody<String> updatePerson(PersonReq personReq) {
        if (personService.updatePersonInfo(personReq) == 1) {
            return ResponseBody.success(String.format("修改%s的信息成功！", personReq.getName()));
        }
        return ResponseBody.failure("400163", "修改个人信息失败");
    }
}