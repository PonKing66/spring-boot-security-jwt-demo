package com.ponking.security.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ponking.system.entity.User;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Peng
 * @date 2020/7/13--22:19
 **/
public class JwtUserDetails implements UserDetails {

    private Integer userId;
    private String username;
    private String password;
    private Integer status;
    private List<String> permissions;


    public JwtUserDetails(User user,List<String> permissions){
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.status = user.getStatus();
        this.permissions = permissions;
    }

    /**
     * 认证
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.permissions.stream()
                .map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    /**
     * 账号是否失效
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     *  账号是否被锁
     * "状态  0：正常   1：禁用   2：锁定
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return status != 2;
    }

    /**
     * 秘密是否失效
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 是否可用
     * @return
     */
    @Override
    public boolean isEnabled() {
        return this.status != 1;
    }
}
