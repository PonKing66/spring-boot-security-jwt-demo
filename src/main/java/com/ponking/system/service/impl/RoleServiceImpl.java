package com.ponking.system.service.impl;

import com.ponking.system.entity.Role;
import com.ponking.system.dao.RoleDao;
import com.ponking.system.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author peng
 * @since 2020-07-13
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleDao, Role> implements RoleService {

}
