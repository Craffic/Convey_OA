package com.craffic.convey.server.service;

import com.craffic.convey.server.dao.CvRoleMapper;
import com.craffic.convey.server.dao.CvUserMapper;
import com.craffic.convey.server.model.CvRole;
import com.craffic.convey.server.model.CvUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class CvUserService implements UserDetailsService {

    @Autowired
    private CvUserMapper userMapper;

    @Autowired
    private CvRoleMapper roleMapper;
    /**
     * 查询所有系统用户
     */
    public List<CvUser> queryAllUsers(){
        List<CvUser> userList = userMapper.getAllUsers();
        if (CollectionUtils.isEmpty(userList)) {
            return new ArrayList();
        }
        userList.stream().forEach(user -> {
            List<CvRole> roleList = roleMapper.getRolesByUserId(user.getId());
            user.setRoles(roleList);
        });
        return userList;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        CvUser cvUser = userMapper.loadUserByUserName(userName);
        if (cvUser == null) {
            throw new UsernameNotFoundException("用户名不存在！");
        }
        // 登录成功后，给用户setRoles
        List<CvRole> roleList = roleMapper.getRolesByUserId(cvUser.getId());
        cvUser.setRoles(roleList);
        return cvUser;
    }

    /**
     * 查询登录人信息
     * @return
     */
    public CvUser queryLoginUser(){
        return (CvUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
