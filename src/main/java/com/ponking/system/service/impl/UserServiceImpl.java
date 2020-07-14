package com.ponking.system.service.impl;

import com.ponking.system.entity.User;
import com.ponking.system.dao.UserDao;
import com.ponking.system.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 系统用户 服务实现类
 * </p>
 *
 * @author peng
 * @since 2020-07-13
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<String> getUserPermissions(String username) {
        return userDao.selectUserPermissions(username);
    }

    @Override
    public List<String> getUserRoles(String username) {
        return userDao.selectUserRoles(username);
    }
}
