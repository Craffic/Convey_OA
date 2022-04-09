package com.craffic.convey.server.controller;

import com.craffic.convey.common.response.ResponseBody;
import com.craffic.convey.common.vo.ListVo;
import com.craffic.convey.server.enums.GenderEnum;
import com.craffic.convey.server.enums.WorkStatEnum;
import com.craffic.convey.server.model.CvUser;
import com.craffic.convey.server.req.CvUserReq;
import com.craffic.convey.server.service.CvUserService;
import com.craffic.convey.server.vo.CvUserVO;
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

    @Autowired
    private CvUserService userService;

    @GetMapping("/query_all")
    public ResponseBody<ListVo<CvUserVO>> queryAllUsers(){
        List<CvUser> userList = userService.queryAllUsers();
        ListVo<CvUserVO> listVO = new ListVo<>(null, 0);
        if (CollectionUtils.isEmpty(userList)) {
            return ResponseBody.success(listVO);
        }

        List<CvUserVO> userVoList = new ArrayList<>();
        userList.stream().forEach(user -> {
            CvUserVO userVo = new CvUserVO();
            BeanUtils.copyProperties(user, userVo);
            userVoList.add(userVo);
        });
        ListVo<CvUserVO> listVo = new ListVo<>(userVoList, userVoList.size());
        return ResponseBody.success(listVo);
    }

    /**
     * 根据条件查询用户列表
     * @return
     */
    @GetMapping("/query")
    public ResponseBody<ListVo<CvUserVO>> queryUsersByCondition(CvUserReq user){
        ListVo<CvUser> cvUserListVo = userService.queryUsersByCondition(user);
        List<CvUser> list = cvUserListVo.getList();
        List<CvUserVO> userVoList = new ArrayList<>();
        list.stream().forEach(cvUser -> {
            CvUserVO userVo = new CvUserVO();
            BeanUtils.copyProperties(cvUser, userVo);
            WorkStatEnum workStatEnum = WorkStatEnum.parseByValue(cvUser.getWorkStat());
            userVo.setWorkStatDesc(workStatEnum.desc());
            GenderEnum genderEnum = GenderEnum.parseByValue(cvUser.getGender());
            userVo.setGenderDesc(genderEnum.desc());
            userVoList.add(userVo);
        });
        ListVo<CvUserVO> listVo = new ListVo<>(userVoList, cvUserListVo.getTotalNum());
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
}
