package com.craffic.convey.server.controller;

import com.craffic.convey.server.model.CvUser;
import com.craffic.convey.server.service.CvUserService;
import com.craffic.convey.server.vo.CvUserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class CvUserController {

    @Autowired
    private CvUserService userService;


    @GetMapping("/all")
    public List<CvUserVO> queryAllUsers(){
        List<CvUser> userList = userService.queryAllUsers();
        List<CvUserVO> resultList = new ArrayList<>();
        userList.stream().forEach(user -> {
            CvUserVO userVO = new CvUserVO();
            BeanUtils.copyProperties(user, userVO);
            resultList.add(userVO);
        });
        return resultList;
    }

}
