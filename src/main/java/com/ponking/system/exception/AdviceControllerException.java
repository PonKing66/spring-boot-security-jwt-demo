package com.ponking.system.exception;

import com.ponking.security.utils.ResultUtils;
import com.ponking.system.aop.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常捕获
 * @author Peng
 * @date 2020/7/15--17:57
 **/
@Slf4j
@ControllerAdvice
public class AdviceControllerException {

    @ExceptionHandler(Exception.class)
    public void authenticationException(Exception exception, HttpServletResponse response) {
        exception.printStackTrace();
        ResultUtils.setResponseJson(response, new ResultUtils.Result(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "系统出错"));
    }
}
