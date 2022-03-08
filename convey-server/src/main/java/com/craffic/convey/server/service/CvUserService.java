package com.craffic.convey.server.service;

import com.craffic.convey.server.dao.CvUserMapper;
import com.craffic.convey.server.model.CvUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CvUserService {

    @Autowired
    private CvUserMapper userMapper;

    /**
     * 查询所有系统用户
     */
    public List<CvUser> queryAllusers(){
        return userMapper.getAllUsers();
    }

}
