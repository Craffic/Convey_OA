package com.craffic.convey.server.service;

import com.craffic.convey.server.dao.CvRoleMapper;
import com.craffic.convey.server.model.CvRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CvRoleService {

    @Autowired
    private CvRoleMapper roleMapper;
    /**
     * 查询所有系统用户
     */
    public List<CvRole> queryAllRoles(){
        return roleMapper.getAllRoles();
    }
}
