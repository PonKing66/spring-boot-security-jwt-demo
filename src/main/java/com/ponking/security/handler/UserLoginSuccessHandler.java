package com.ponking.security.handler;

import com.ponking.security.constants.SecurityConstants;
import com.ponking.security.entity.JwtUserDetails;
import com.ponking.security.utils.JwtTokenUtils;
import com.ponking.security.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 登录成功处理类
 * @author Peng
 * @date 2020/7/15--16:30
 **/
@Slf4j
public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("Authentication success");
        JwtUserDetails jwtUserDetails = (JwtUserDetails)authentication.getPrincipal();
        List<String> authorities = jwtUserDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        // 创建token
        String token = JwtTokenUtils.createToken(jwtUserDetails.getUsername(),authorities);
        // Http Response Header 中返回 Token
        response.setHeader(SecurityConstants.TOKEN_HEADER,token);

        ResultUtils.setResponseJson(response,new ResultUtils.Result(HttpServletResponse.SC_OK,"登录成功"));
    }
}
