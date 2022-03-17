package com.craffic.convey.server.config;

import com.craffic.convey.server.common.RespBean;
import com.craffic.convey.server.model.CvUser;
import com.craffic.convey.server.service.CvUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CvUserService userService;

    @Autowired
    private FilterInvocationSecurityMetadataSource custFilter;
    @Autowired
    private CustomUrlDecisionManager desicionManager;

    /**
     * 明文加密
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 全部请求都需要认证才能登录
        http.authorizeRequests()
//                .anyRequest().authenticated()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setAccessDecisionManager(desicionManager);
                        o.setSecurityMetadataSource(custFilter);
                        return o;
                    }
                })
                .and()
                .formLogin().usernameParameter("username").passwordParameter("password")
                .loginProcessingUrl("/doLogin").loginPage("/login").permitAll()
                // 登录成功回调
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        response.setContentType("application/json;charset=utf-8");
                        PrintWriter out = response.getWriter();
                        CvUser currLoginHr = (CvUser) authentication.getPrincipal();
//                        currLoginHr.setPassword(null);
                        RespBean respBean = RespBean.ok("登录成功！", currLoginHr);
                        String result = new ObjectMapper().writeValueAsString(respBean);
                        out.write(result);
                        out.flush();
                        out.close();
                    }
                })
                // 登录失败的回调
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
                        response.setContentType("application/json;charset=utf-8");
                        PrintWriter out = response.getWriter();
                        RespBean respBean = RespBean.error("登录失败！");
                        if (e instanceof LockedException){
                            respBean.setMsg("账户被锁定，请联系管理员！");
                        } else if (e instanceof CredentialsExpiredException) {
                            respBean.setMsg("密码过期，请联系管理员！");
                        } else if (e instanceof AccountExpiredException) {
                            respBean.setMsg("账户过期，请联系管理员！");
                        } else if (e instanceof DisabledException) {
                            respBean.setMsg("账户被禁用，请联系管理员！");
                        } else if (e instanceof BadCredentialsException) {
                            respBean.setMsg("用户名或密码错误，请重新输入！");
                        }
                        out.write(new ObjectMapper().writeValueAsString(respBean));
                        out.flush();
                        out.close();
                    }
                })
                .permitAll()
                .and()
                .logout()
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        response.setContentType("application/json;charset=utf-8");
                        PrintWriter out = response.getWriter();
                        RespBean respBean = RespBean.ok("注销成功！");
                        String result = new ObjectMapper().writeValueAsString(respBean);
                        out.write(result);
                        out.flush();
                        out.close();
                    }
                })
                .permitAll()
                .and().csrf().disable();
    }
}
