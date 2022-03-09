package com.craffic.convey.server.dao;

import com.craffic.convey.server.model.CvUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CvUserMapper {

    List<CvUser> getAllUsers();

    CvUser loadUserByUserName(@Param("acct") String acct);
}
