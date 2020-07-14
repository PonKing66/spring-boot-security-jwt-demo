package com.ponking.system.service.impl;

import com.ponking.system.entity.RoleMenu;
import com.ponking.system.dao.RoleMenuDao;
import com.ponking.system.service.RoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色与菜单对应关系 服务实现类
 * </p>
 *
 * @author peng
 * @since 2020-07-13
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuDao, RoleMenu> implements RoleMenuService {

}
