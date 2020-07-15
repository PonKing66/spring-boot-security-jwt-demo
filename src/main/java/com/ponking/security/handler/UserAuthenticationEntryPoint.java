package com.ponking.security.handler;

import com.ponking.security.utils.ResultUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用来解决匿名用户访问需要权限才能访问的资源时的异常
 * @author Peng
 * @date 2020/7/14--9:38
 **/
public class UserAuthenticationEntryPoint implements AuthenticationEntryPoint {

    /**
     * 当用户尝试访问需要权限才能的REST资源而不提供
     * Token或者Token错误获取因过期时间
     * @param request
     * @param response
     * @param authException
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ResultUtils.setResponseJson(response,new ResultUtils.Result(HttpServletResponse.SC_UNAUTHORIZED,"未登录"));
    }
}
