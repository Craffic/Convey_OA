package com.craffic.convey.server.service;

import com.craffic.convey.server.dao.CvMenuMapper;
import com.craffic.convey.server.model.CvMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CvMenuService {

    @Autowired
    private CvMenuMapper menuMapper;

    List<CvMenu> getAllMenusWithRole(){
        return menuMapper.getAllMenusWithRole();
    }
}
