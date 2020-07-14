package com.ponking.system.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Peng
 * @date 2020/7/14--22:33
 **/
@Aspect
@Component
@Slf4j
public class LogAspect {

    @Pointcut("@annotation(com.ponking.system.aop.Log)")
    public void pointCut() {

    }

    @Around(value = "pointCut()")
    public Object aroundMethod(ProceedingJoinPoint pjp) throws Throwable {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("用户[{}]执行了: "+request.getRequestURI(),username);
        return pjp.proceed();
    }
}
