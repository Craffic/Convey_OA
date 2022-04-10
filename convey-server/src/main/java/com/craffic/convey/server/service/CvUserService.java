package com.craffic.convey.server.service;

import com.craffic.convey.common.vo.ListVo;
import com.craffic.convey.server.dao.CvRoleMapper;
import com.craffic.convey.server.dao.CvUserMapper;
import com.craffic.convey.server.enums.GenderEnum;
import com.craffic.convey.server.enums.WorkStatEnum;
import com.craffic.convey.server.model.CvRole;
import com.craffic.convey.server.model.CvUser;
import com.craffic.convey.server.req.CvUserReq;
import com.craffic.convey.server.utils.PasswordEncoder;
import com.craffic.convey.server.vo.CvUserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    /**
     * 根据条件查询用户列表
     */
    public ListVo<CvUser> queryUsersByCondition(CvUserReq userReq){
        userReq.setStartRecord(userReq.pageStartIndex());
        userReq.setEndRecord(userReq.getPage() * userReq.getSize());
        Long total = userMapper.queryTotalNum(userReq);
        if (total <= 0) {
            return new ListVo<>(null, 0);
        }
        List<CvUser> userList = userMapper.queryUsersByCondition(userReq);
        return new ListVo<>(userList, total.intValue());
    }

    /**
     * 生成用户工号
     */
    public Long generateWorkId(){
        return userMapper.generateWorkId();
    }

    /**
     * 添加用户
     */
    public Integer addUser(CvUser user){
        return userMapper.addUser(user);
    }

    /**
     * 根据用户id删除记录
     */
    public Integer deleteUserById(Long id){
        return userMapper.deleteUserById(id);
    }

    /**
     * 更新用户
     */
    public Integer updateUser(CvUser user){
        return userMapper.updateUser(user);
    }

    /**
     * 修改密码
     */
    public String modifyPassword(Map<String, Object> pwdMap){
        String idCardNo = (String)pwdMap.get("idCardNo");

        // 校验用户是否存在
        CvUserReq userReq = new CvUserReq();
        userReq.setIdCardNo(idCardNo);
        ListVo<CvUser> listVo = queryUsersByCondition(userReq);
        if (listVo.getTotalNum() == 0) {
            return "不存在该用户，请联系管理员";
        }

        // 更新密码
        CvUser currUser = listVo.getList().get(0);
        String newPass = (String)pwdMap.get("newPass");
        currUser.setPassword(PasswordEncoder.encode(newPass));
        Integer flag = updateUser(currUser);
        if (flag == 1) {
            return "";
        } else {
            return "更新密码失败！";
        }
    }

    /**
     * 根据省份证号获取个人中心信息
     * @param idCardNo
     * @return
     */
    public CvUserVo profile(String idCardNo) {
        CvUser profile = userMapper.profile(idCardNo);
        CvUserVo cvUserVo = new CvUserVo();
        BeanUtils.copyProperties(profile, cvUserVo);
        // 转换枚举
        cvUserVo.setGenderDesc(GenderEnum.parseByValue(profile.getGender()).desc());
        cvUserVo.setWorkStatDesc(WorkStatEnum.parseByValue(profile.getWorkStat()).desc());
        return cvUserVo;
    }
}
