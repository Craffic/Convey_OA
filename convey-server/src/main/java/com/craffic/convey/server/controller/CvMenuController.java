package com.craffic.convey.server.controller;

import com.craffic.convey.server.model.CvMenu;
import com.craffic.convey.server.model.CvUser;
import com.craffic.convey.server.service.CvMenuService;
import com.craffic.convey.server.service.CvUserService;
import com.craffic.convey.server.vo.CvUserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CvMenuController {

    @Autowired
    private CvMenuService menuService;

    @Autowired
    private CvUserService userService;

    /**
     * 查询登录人所有的菜单
     */
    @GetMapping("/menu")
    public List<CvMenu> getMenuByHrId() throws Exception {
        CvUser loginUser = userService.queryLoginUser();
        return menuService.getMenusByUserId(loginUser.getId());
    }

}
