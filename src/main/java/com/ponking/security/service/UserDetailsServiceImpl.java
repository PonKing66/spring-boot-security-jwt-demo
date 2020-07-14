package com.ponking.security.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ponking.security.entity.JwtUserDetails;
import com.ponking.system.entity.User;
import com.ponking.system.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Peng
 * @date 2020/7/13--22:16
 **/
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<String> authorities = userService.getUserPermissions(username);
//        List<String> authorities  = userService.getUserRoles(username);
        User user = userService.getOne(new QueryWrapper<User>().eq("username", username));
        return new JwtUserDetails(user,authorities);
    }
}
