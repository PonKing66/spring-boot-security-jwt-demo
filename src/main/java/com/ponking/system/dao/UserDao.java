package com.ponking.system.dao;

import com.ponking.system.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * <p>
 * 系统用户 Mapper 接口
 * </p>
 *
 * @author peng
 * @since 2020-07-13
 */
public interface UserDao extends BaseMapper<User> {

    List<String> selectUserPermissions(@Param("name") String username);

    List<String> selectUserRoles(@Param("name")String username);
}
