package com.ponking.security.handler;

import com.ponking.security.utils.ResultUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用来解决认证过的用户访问无权限资源时的异常
 * @author Peng
 * @date 2020/7/14--9:37
 **/
public class UserAccessDeniedHandler implements AccessDeniedHandler {

    /**
     * 当用户尝试访问需要权限才能的REST资源而权限不足的时候，
     * 将调用此方法发送403响应以及错误信息
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ResultUtils.setResponseJson(response,new ResultUtils.Result(HttpServletResponse.SC_FORBIDDEN,"你未有该权限访问"));
    }
}
