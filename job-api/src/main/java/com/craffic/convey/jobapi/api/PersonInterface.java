package com.craffic.convey.jobapi.api;

import com.craffic.convey.common.response.ResponseBody;
import com.craffic.convey.common.vo.ListVo;
import com.craffic.convey.jobapi.req.PersonReq;
import com.craffic.convey.jobapi.vo.PersonVo;

public interface PersonInterface {
    String sayHello();

    ResponseBody<ListVo<PersonVo>> queryPersonsByPage(PersonReq personReq);

    ResponseBody<PersonVo> queryPersonInfo(String idCardNo);
}
