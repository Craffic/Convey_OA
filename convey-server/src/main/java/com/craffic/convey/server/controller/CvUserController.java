package com.craffic.convey.server.controller;

import com.craffic.convey.server.common.ListVo;
import com.craffic.convey.server.common.ResponseBody;
import com.craffic.convey.server.model.CvUser;
import com.craffic.convey.server.req.CvUserReq;
import com.craffic.convey.server.service.CvUserService;
import com.craffic.convey.server.vo.CvUserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
public class CvUserController {

    @Autowired
    private CvUserService userService;

    @GetMapping("/query_all")
    public ResponseBody<ListVo<CvUserVO>> queryAllUsers(){
        List<CvUser> userList = userService.queryAllUsers();
        ListVo<CvUserVO> listVO = new ListVo<>(null, 0);
        if (CollectionUtils.isEmpty(userList)) {
            return ResponseBody.success(listVO);
        }

        List<CvUserVO> userVoList = new ArrayList<>();
        userList.stream().forEach(user -> {
            CvUserVO userVo = new CvUserVO();
            BeanUtils.copyProperties(user, userVo);
            userVoList.add(userVo);
        });
        ListVo<CvUserVO> listVo = new ListVo<>(userVoList, userVoList.size());
        return ResponseBody.success(listVo);
    }

    /**
     * 根据条件查询用户列表
     * @return
     */
    @GetMapping("/query")
    public ResponseBody<ListVo<CvUserVO>> queryUsersByCondition(CvUserReq user){
        ListVo<CvUser> cvUserListVo = userService.queryUsersByCondition(user);
        List<CvUser> list = cvUserListVo.getList();
        List<CvUserVO> userVoList = new ArrayList<>();
        list.stream().forEach(cvUser -> {
            CvUserVO userVo = new CvUserVO();
            BeanUtils.copyProperties(cvUser, userVo);
            userVoList.add(userVo);
        });
        ListVo<CvUserVO> listVo = new ListVo<>(userVoList, userVoList.size());
        return ResponseBody.success(listVo);
    }
}
