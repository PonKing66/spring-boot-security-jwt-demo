package com.ponking.system.service.impl;

import com.ponking.system.entity.UserRole;
import com.ponking.system.dao.UserRoleDao;
import com.ponking.system.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户与角色对应关系 服务实现类
 * </p>
 *
 * @author peng
 * @since 2020-07-13
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleDao, UserRole> implements UserRoleService {

}
