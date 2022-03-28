package com.craffic.convey.server.dao;

import com.craffic.convey.server.model.CvUser;
import com.craffic.convey.server.req.CvUserReq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CvUserMapper {

    List<CvUser> getAllUsers();

    CvUser loadUserByUserName(@Param("acct") String acct);

    List<CvUser> queryUsersByCondition(@Param("user") CvUserReq user);

    Long queryTotalNum(@Param("user") CvUserReq user);
}
