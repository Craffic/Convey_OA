package com.craffic.convey.server.dao;

import com.craffic.convey.server.model.CvMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CvMenuMapper {

    List<CvMenu> getAllMenus();

    List<CvMenu> getAllMenusWithRole();
}