package com.craffic.convey.server.controller.social;

import com.alibaba.dubbo.config.annotation.Reference;
import com.craffic.convey.common.response.ResponseBody;
import com.craffic.convey.common.vo.ListVo;
import com.craffic.convey.jobapi.api.PersonInterface;
import com.craffic.convey.jobapi.req.PersonReq;
import com.craffic.convey.jobapi.vo.PersonVo;
import org.springframework.web.bind.annotation.*;

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
     @PostMapping(value = "/get/person")
     public ResponseBody<ListVo<PersonVo>> queryPersonList(@RequestBody PersonReq personReq){
         ResponseBody<ListVo<PersonVo>> listVoResponseBody = personInterface.queryPersonsByPage(personReq);
         return listVoResponseBody;
     }

    /**
     * 根据身份证号查询个人信息
     * @param idCardNo
     * @return
     */
    @GetMapping(value = "query")
    public ResponseBody<PersonVo> queryPersonInfo(@RequestParam("idCardNo") String idCardNo) {
        return personInterface.queryPersonInfo(idCardNo);
    }
}
