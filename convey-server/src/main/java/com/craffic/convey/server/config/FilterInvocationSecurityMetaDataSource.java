package com.craffic.convey.server.config;

import com.craffic.convey.server.dao.CvMenuMapper;
import com.craffic.convey.server.model.CvMenu;
import com.craffic.convey.server.model.CvRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

@Component
public class FilterInvocationSecurityMetaDataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    CvMenuMapper menuMapper;

    AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        List<CvMenu> allMenus = menuMapper.getAllMenusWithRole();
        for (CvMenu menu : allMenus) {
            if (antPathMatcher.match(menu.getPattern(), requestUrl)) {
                // 获取全部角色
                List<CvRole> roles = menu.getRoles();
                String[] str = new String[roles.size()];
                for (int i = 0; i < roles.size(); i++) {
                    str[i] = roles.get(i).getRole();
                }
                return SecurityConfig.createList(str);
            }
        }
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
