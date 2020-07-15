package com.ponking.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ponking.security.constants.SecurityConstants;
import com.ponking.security.entity.JwtUserDetails;
import com.ponking.security.utils.JwtTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**如果用户名和密码正确，那么过滤器将创建一个JWT Token
 * 并在HTTP Response 的header中返回它，格式：token: "Bearer +具体token值"
 *
 *
 * 2020年7月15日
 * -- 添加 response json返回json
 * @see  com.ponking.security.handler.UserAuthenticationProvider
 * @author Peng
 * @date 2020/7/13--23:01
 **/
@Slf4j
@Deprecated
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final ThreadLocal<Boolean> rememberMe = new ThreadLocal<>();
    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        // 设置登录url
        super.setFilterProcessesUrl(SecurityConstants.AUTH_LOGIN_URL);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try{
            // 获取登录信息
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            boolean is_rememberMe = "true".equals(request.getParameter("rememberMe")) ? true:false;
            rememberMe.set(is_rememberMe);
            // 重写UsernamePasswordAuthenticationToken
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username,password);
            return authenticationManager.authenticate(authentication);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 登录成功时 执行的操作
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        log.info("Authentication success");
        JwtUserDetails jwtUserDetails = (JwtUserDetails)authResult.getPrincipal();
        List<String> authorities = jwtUserDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        // 创建token
        String token = JwtTokenUtils.createToken(jwtUserDetails.getUsername(),authorities,rememberMe.get());
        rememberMe.remove();
        // Http Response Header 中返回 Token
        response.setHeader(SecurityConstants.TOKEN_HEADER,token);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    /**
     * 登录失败时 执行的操作
     * @param request
     * @param response
     * @param authenticationException
     * @throws IOException
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException) throws IOException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authenticationException.getMessage());
    }
}
