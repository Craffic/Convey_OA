package com.craffic.convey.server.controller;

import com.craffic.convey.common.enums.GenderEnum;
import com.craffic.convey.common.response.ResponseBody;
import com.craffic.convey.common.vo.ListVo;
import com.craffic.convey.server.enums.WorkStatEnum;
import com.craffic.convey.server.model.CvUser;
import com.craffic.convey.server.model.OaDict;
import com.craffic.convey.server.req.CvUserReq;
import com.craffic.convey.server.service.CvUserService;
import com.craffic.convey.server.service.OaDictService;
import com.craffic.convey.server.vo.CvUserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class CvUserController {

    public static final String POSITION = "POSITION";

    @Autowired
    private CvUserService userService;
    @Autowired
    private OaDictService dictService;

    @GetMapping("/query_all")
    public ResponseBody<ListVo<CvUserVo>> queryAllUsers(){
        List<CvUser> userList = userService.queryAllUsers();
        ListVo<CvUserVo> listVO = new ListVo<>(null, 0);
        if (CollectionUtils.isEmpty(userList)) {
            return ResponseBody.success(listVO);
        }

        List<CvUserVo> userVoList = new ArrayList<>();
        userList.stream().forEach(user -> {
            CvUserVo userVo = new CvUserVo();
            BeanUtils.copyProperties(user, userVo);
            userVoList.add(userVo);
        });
        ListVo<CvUserVo> listVo = new ListVo<>(userVoList, userVoList.size());
        return ResponseBody.success(listVo);
    }

    /**
     * 根据条件查询用户列表
     * @return
     */
    @GetMapping("/query")
    public ResponseBody<ListVo<CvUserVo>> queryUsersByCondition(CvUserReq user){
        ListVo<CvUser> cvUserListVo = userService.queryUsersByCondition(user);
        List<CvUser> list = cvUserListVo.getList();
        List<CvUserVo> userVoList = new ArrayList<>();
        list.stream().forEach(cvUser -> {
            CvUserVo userVo = new CvUserVo();
            BeanUtils.copyProperties(cvUser, userVo);
            WorkStatEnum workStatEnum = WorkStatEnum.parseByValue(cvUser.getWorkStat());
            userVo.setWorkStatDesc(workStatEnum.desc());
            GenderEnum genderEnum = GenderEnum.parseByValue(cvUser.getGender());
            userVo.setGenderDesc(genderEnum.desc());
            // 转换职位
            OaDict position = dictService.queryDictByKey(cvUser.getPosId(), POSITION);
            userVo.setPosDesc(position == null ? null : position.getValue());
            userVoList.add(userVo);
        });
        ListVo<CvUserVo> listVo = new ListVo<>(userVoList, cvUserListVo.getTotalNum());
        return ResponseBody.success(listVo);
    }

    @PostMapping("/add")
    public ResponseBody<String> addUser(@RequestBody CvUser user) {
        Long workId = userService.generateWorkId();
        user.setId(workId);
        user.setWorkId(workId);
        if (userService.addUser(user) == 1) {
            return ResponseBody.success("添加用户" + user.getAcct() + "成功！");
        }
        return ResponseBody.failure("400101", "添加用户失败！");
    }

    /**
     * 获取最大的工号
     * @return
     */
    @GetMapping("maxWorkID")
    public ResponseBody<Long> getMaxWorkId(){
        Long workId = userService.generateWorkId();
        return ResponseBody.success(workId);
    }

    /**
     * 根据id删除员工
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseBody<String> deleteUserById(@PathVariable Long id) {
        if (userService.deleteUserById(id) == 1) {
            return ResponseBody.success("删除成功!");
        }
        return ResponseBody.failure("400102", "删除用户失败!");
    }

    /**
     * 更新用户
     */
    @PutMapping("/update")
    public ResponseBody<String> updateUser(@RequestBody CvUser user) {
        if (userService.updateUser(user) == 1) {
            return ResponseBody.success("更新成功");
        }
        return ResponseBody.failure("400103", "更新用户失败！");
    }

    /**
     * 修改密码
     */
    @PutMapping("/modifyPwd")
    public ResponseBody<String> modifyPassword(@RequestBody Map<String, Object> passwordMap){
        String errResult = userService.modifyPassword(passwordMap);
        if (StringUtils.hasText(errResult)){
            return ResponseBody.failure("400104", errResult);
        }
        return ResponseBody.success("更新密码成功！");
    }

    /**
     * 个人中心
     */
    @GetMapping("/profile")
    public ResponseBody<CvUserVo> profile(String idCardNo){
        CvUserVo profile = userService.profile(idCardNo);
        return ResponseBody.success(profile);
    }
}
