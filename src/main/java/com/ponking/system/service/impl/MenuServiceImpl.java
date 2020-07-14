package com.ponking.system.service.impl;

import com.ponking.system.entity.Menu;
import com.ponking.system.dao.MenuDao;
import com.ponking.system.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜单管理 服务实现类
 * </p>
 *
 * @author peng
 * @since 2020-07-13
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuDao, Menu> implements MenuService {

}
