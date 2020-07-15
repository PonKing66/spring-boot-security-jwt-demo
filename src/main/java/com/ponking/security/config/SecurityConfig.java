package com.ponking.security.config;

import com.ponking.security.filter.JwtAuthenticationFilter;
import com.ponking.security.filter.JwtAuthorizationFilter;
import com.ponking.security.handler.*;
import com.ponking.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Spring Security配置类
 *
 * @author Peng
 * @date 2020/7/13--22:13
 **/
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //开启权限注解,默认是关闭的
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UserAuthenticationProvider userAuthenticationProvider;


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 指定路径无条件允许访问
                .antMatchers(HttpMethod.POST, "/auth/login", "/auth/logout").permitAll()
                // 指定路径需要登陆后才能访问
                .antMatchers("/api/sys/**").authenticated()
                // 其余路径能访问
                .anyRequest().permitAll()
                // 登录操作
                .and()
                .formLogin()
                .loginProcessingUrl("/auth/login")
                .successHandler(new UserLoginSuccessHandler())
                .failureHandler(new UserLoginFailureHandler())
                .and()
                // 注销操作
                .logout()
                .logoutUrl("/auth/logout")
                .logoutSuccessHandler(new UserLogoutSuccessHandler())
                .and()
                //添加自定义Filter
//                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), userDetailsService))
                // 不需要session（不创建会话）
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // 授权异常处理
                .exceptionHandling().authenticationEntryPoint(new UserAuthenticationEntryPoint())
                .accessDeniedHandler(new UserAccessDeniedHandler())
                .and()
                //禁用csrf
                .cors().and().csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
        auth.authenticationProvider(userAuthenticationProvider);
    }
}
