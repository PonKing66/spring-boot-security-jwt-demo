package com.ponking.system.demo;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Peng
 * @date 2020/7/14--10:17
 **/
public class BCryptPasswordEncoderTest {
    @Test
    public void test01(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode("123456");
        //$2a$10$wU/6wvTzRc/3Fbnkqsv.pupXnNtMNhvt.FxUehGlPlEPi1m6qqH.S
        System.out.println(encode);
    }
}
