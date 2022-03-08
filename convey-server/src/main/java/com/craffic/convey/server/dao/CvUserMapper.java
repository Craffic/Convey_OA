package com.craffic.convey.server.dao;

import com.craffic.convey.server.model.CvUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CvUserMapper {

    List<CvUser> getAllUsers();

}
