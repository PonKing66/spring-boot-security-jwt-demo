package com.ponking.system.service;

import com.ponking.system.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 系统用户 服务类
 * </p>
 *
 * @author peng
 * @since 2020-07-13
 */
public interface UserService extends IService<User> {

    /**
     * 根据用户获取权限值
     * @param username
     * @return
     */
    List<String> getUserPermissions(String username);

    /**
     * 根据用户获取角色
     * @param username
     * @return
     */
    List<String> getUserRoles(String username);
}
