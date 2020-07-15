package com.ponking.security.handler;

import com.ponking.security.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录失败处理类
 * @author Peng
 * @date 2020/7/15--16:25
 **/
@Slf4j
public class UserLoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        if(exception instanceof UsernameNotFoundException){
            log.warn("登录失败,用户名不存在");
            ResultUtils.setResponseJson(response,new ResultUtils.Result(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"用户名不存在"));
        }else if (exception instanceof LockedException){
            log.warn("登录失败,用户被冻结");
            ResultUtils.setResponseJson(response,new ResultUtils.Result(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"用户被冻结"));
        }else if(exception instanceof BadCredentialsException){
            log.warn("登录失败,用户登录密码错误");
            ResultUtils.setResponseJson(response,new ResultUtils.Result(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"密码错误"));
        }else {
            ResultUtils.setResponseJson(response,new ResultUtils.Result(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"登录失败"));
        }
    }
}
