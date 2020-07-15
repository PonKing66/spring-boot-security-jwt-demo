package com.ponking.security.handler;


import com.ponking.security.service.UserDetailsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * UserAuthenticationProvider JwtAuthenticationFilter 两者选期一
 *
 * @author Peng
 * @date 2020/7/15--18:07
 * @see com.ponking.security.filter.JwtAuthenticationFilter
 **/
@Component
@Slf4j
public class UserAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsServiceImpl userDetailsDetailsService;

    public UserAuthenticationProvider(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("get authenticate");
        // 获取表单输入中返回的用户名
        String username = (String) authentication.getPrincipal();
        // 获取表单中输入的密码
        String password = (String) authentication.getCredentials();
        // 查询用户是否存在
        UserDetails userDetails = userDetailsDetailsService.loadUserByUsername(username);

        if (userDetails.getUsername() == null || userDetails.getUsername().equals("")) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        // 我们还要判断密码是否正确，这里我们的密码使用BCryptPasswordEncoder进行加密的
        if (!new BCryptPasswordEncoder().matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("密码不正确");
        }
        // 还可以加一些其他信息的判断，比如用户账号已停用等判断
        if (!userDetails.isAccountNonLocked()) {
            throw new LockedException("该用户已被冻结");
        }
        if (!userDetails.isEnabled()) {
            throw new DisabledException("该用户已被不可用");
        }
        if (!userDetails.isAccountNonExpired()) {
            throw new DisabledException("该用户认证已过期");
        }
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        // 进行登录
        return new UsernamePasswordAuthenticationToken(userDetails, password, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
