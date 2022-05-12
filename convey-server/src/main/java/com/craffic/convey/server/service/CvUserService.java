package com.craffic.convey.server.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.craffic.convey.common.enums.GenderEnum;
import com.craffic.convey.common.response.ResponseBody;
import com.craffic.convey.common.vo.ListVo;
import com.craffic.convey.jobapi.api.PersonInterface;
import com.craffic.convey.jobapi.req.PersonReq;
import com.craffic.convey.jobapi.vo.PersonVo;
import com.craffic.convey.server.dao.CvRoleMapper;
import com.craffic.convey.server.dao.CvUserMapper;
import com.craffic.convey.server.enums.WorkStatEnum;
import com.craffic.convey.server.model.CvRole;
import com.craffic.convey.server.model.CvUser;
import com.craffic.convey.server.model.OaDict;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CvUserService implements UserDetailsService {

    public static final String POSITION = "POSITION";
    @Autowired
    private CvUserMapper userMapper;

    @Autowired
    private CvRoleMapper roleMapper;

    @Autowired
    private OaDictService dictService;

    @Reference
    PersonInterface personInterface;

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
    public ListVo<CvUserVo> queryUsersByCondition(CvUserReq userReq){
        userReq.setStartRecord(userReq.pageStartIndex());
        userReq.setEndRecord(userReq.getPage() * userReq.getSize());
        Long total = userMapper.queryTotalNum(userReq);
        if (total <= 0) {
            return new ListVo<>(new ArrayList<>(), 0);
        }
        List<CvUser> userList = userMapper.queryUsersByCondition(userReq);
        List<CvUserVo> userVoList = new ArrayList<>();
        userList.stream().forEach(cvUser -> {
            CvUserVo userVo = new CvUserVo();
            BeanUtils.copyProperties(cvUser, userVo);
            WorkStatEnum workStatEnum = WorkStatEnum.parseByValue(cvUser.getWorkStat());
            userVo.setWorkStatDesc(workStatEnum.desc());
            GenderEnum genderEnum = GenderEnum.parseByValue(cvUser.getGender());
            userVo.setGenderDesc(genderEnum.desc());
            // 转换职位
            OaDict position = dictService.queryDictByKey(cvUser.getPosId(), POSITION);
            userVo.setPosDesc(position == null ? null : position.getValue());
            List<String> homeAddress = new ArrayList();
            List<String> workAddress = new ArrayList();
            ResponseBody<PersonVo> personResponse = personInterface.queryPersonInfo(userVo.getIdCardNo());
            PersonVo obj = personResponse.getObj();
            if (obj != null) {
                homeAddress.add(obj.getHomeProvince().toString());
                homeAddress.add(obj.getHomeCity().toString());
                homeAddress.add(obj.getHomeArea().toString());
                workAddress.add(obj.getWorkProvince().toString());
                workAddress.add(obj.getWorkCity().toString());
                workAddress.add(obj.getWorkArea().toString());
                userVo.setHomeAddress(homeAddress);
                userVo.setWorkAddress(workAddress);
                userVo.setAge(obj.getAge());
            }
            userVoList.add(userVo);
        });
        return new ListVo(userVoList, total.intValue());
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
        // 初始化密码
        String encPassword = PasswordEncoder.encode("123456");
        user.setPassword(encPassword);
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
    @Transactional
    public Integer updateUser(CvUserReq userReq){
        List<String> homeAddress = userReq.getHomeAddress();
        List<String> workAddress = userReq.getWorkAddress();
        if (!CollectionUtils.isEmpty(homeAddress)) {
            userReq.setHomeProvince(homeAddress.get(0));
            userReq.setHomeCity(homeAddress.get(1));
            userReq.setHomeArea(homeAddress.get(2));
        }
        if (!CollectionUtils.isEmpty(workAddress)) {
            userReq.setWorkProvince(workAddress.get(0));
            userReq.setWorkCity(workAddress.get(1));
            userReq.setWorkArea(workAddress.get(2));
        }
        Integer flag = userMapper.updateUser(userReq);
        if (flag == 1 && updateUserBaseInfo(userReq)) {
            return 1;
        }
        return 0;
    }

    /**
     * 调用job-api的接口来更新用户的基本信息
     * @param userReq
     * @return
     */
    @Transactional
    public Boolean updateUserBaseInfo(CvUserReq userReq){
        PersonReq personReq = new PersonReq();
        BeanUtils.copyProperties(userReq, personReq);
        ResponseBody<String> responseBody = personInterface.updatePerson(personReq);
        return responseBody.getCode().equals("200000");
    }

    /**
     * 修改密码
     */
    public String modifyPassword(Map<String, Object> pwdMap){
        String idCardNo = (String)pwdMap.get("idCardNo");

        // 校验用户是否存在
        CvUserReq userReq = new CvUserReq();
        userReq.setIdCardNo(idCardNo);
        ListVo<CvUserVo> listVo = queryUsersByCondition(userReq);
        if (listVo.getTotalNum() == 0) {
            return "不存在该用户，请联系管理员";
        }

        // 更新密码
        CvUserVo currUser = listVo.getList().get(0);
        BeanUtils.copyProperties(currUser, userReq);
        String newPass = (String)pwdMap.get("newPass");
        currUser.setPassword(PasswordEncoder.encode(newPass));
        Integer flag = updateUser(userReq);
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
