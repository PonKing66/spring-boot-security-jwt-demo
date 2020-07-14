package com.ponking.security.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author Peng
 * @date 2020/7/13--16:11
 **/
@Data
public class PayLoad<T> {
    private String id;
    private T userInfo;
    private Date expiration;
}
