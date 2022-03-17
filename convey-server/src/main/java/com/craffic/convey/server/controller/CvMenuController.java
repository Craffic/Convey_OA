package com.craffic.convey.server.controller;

import com.craffic.convey.server.model.CvMenu;
import com.craffic.convey.server.model.CvUser;
import com.craffic.convey.server.service.CvMenuService;
import com.craffic.convey.server.service.CvUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("/menu/currUser")
    public List<CvMenu> getMenuByCurrUser() throws Exception {
        CvUser loginUser = userService.queryLoginUser();
        return menuService.getMenusByUserId(loginUser.getId());
    }

    @GetMapping("/menu/{userId}")
    public List<CvMenu> getMenuByUserId(@PathVariable(value = "userId") Long userId) throws Exception {
        List<CvMenu> menus = menuService.getMenusByUserId(userId);
        return menus;
    }
}
