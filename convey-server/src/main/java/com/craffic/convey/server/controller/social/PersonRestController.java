package com.craffic.convey.server.controller.social;

import com.alibaba.dubbo.config.annotation.Reference;
import com.craffic.convey.common.response.ResponseBody;
import com.craffic.convey.common.utils.DateUtil;
import com.craffic.convey.common.vo.ListVo;
import com.craffic.convey.jobapi.api.PersonInterface;
import com.craffic.convey.jobapi.req.PersonReq;
import com.craffic.convey.jobapi.vo.PersonVo;
import com.craffic.convey.server.enums.GenderEnum;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class PersonRestController {

    @Reference
    PersonInterface personInterface;

    @GetMapping(value = "/hello")
    public String sayHello(){
        return personInterface.sayHello();
    }

    /**
     * 分页查询社会人员
     * @param personReq
     * @return
     */
     @GetMapping(value = "/get/person")
     public ResponseBody<ListVo<PersonVo>> queryPersonList(PersonReq personReq){
         ResponseBody<ListVo<PersonVo>> responseBody = personInterface.queryPersonsByPage(personReq);
         if (!responseBody.getCode().equals("200000")) {
             return ResponseBody.failure("400501", String.format("查询社会人员列表失败，失败原因%s", responseBody.getMessage()));
         }
         ListVo<PersonVo> listVo = responseBody.getObj();
         if (listVo.getTotalNum() == 0) {
             return responseBody;
         }
         List<PersonVo> list = listVo.getList();
         for (PersonVo personVo : list) {
             String gender = personVo.getGender();
             GenderEnum genderEnum = GenderEnum.parseByValue(gender);
             personVo.setGenderDesc(genderEnum.desc());
             // 年龄
             int age = DateUtil.calYearDiff(new Date(), personVo.getBirthDate());
             personVo.setAge(age);
         }
         return responseBody;
     }

    /**
     * 根据身份证号查询个人信息
     * @param idCardNo
     * @return
     */
    @GetMapping(value = "query")
    public ResponseBody<PersonVo> queryPersonInfo(@RequestParam("idCardNo") String idCardNo) {
        ResponseBody<PersonVo> responseBody = personInterface.queryPersonInfo(idCardNo);
        if (!responseBody.getCode().equals("200000")) {
            return ResponseBody.failure("400502", String.format("查询社会人员信息失败，失败原因%s", responseBody.getMessage()));
        }
        return responseBody;
    }
}
