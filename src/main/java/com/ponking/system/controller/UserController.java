package com.ponking.system.controller;


import com.ponking.system.aop.Log;
import com.ponking.system.entity.Menu;
import com.ponking.system.entity.User;
import com.ponking.system.service.MenuService;
import com.ponking.system.service.UserService;
import io.jsonwebtoken.lang.Assert;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 系统用户 前端控制器
 * </p>
 *
 * @author peng
 * @since 2020-07-13
 */
@RestController
@RequestMapping("/api/sys/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Log
    @GetMapping
    @PreAuthorize("hasAnyAuthority('sys:user:menu')")
    public ResponseEntity list() {
        return ResponseEntity.ok().body(userService.list());
    }

    @Log
    @PostMapping
    @PreAuthorize("hasAnyAuthority('sys:user:add')")
    public ResponseEntity save(@RequestBody User user) {
        Assert.notNull(user, "Menu is NUll or Empty");
        userService.save(user);
        return ResponseEntity.ok().build();
    }

    @Log
    @PutMapping("{id}")
    @PreAuthorize("hasAnyAuthority('sys:user:update')")
    public ResponseEntity update(@PathVariable Integer id, @RequestBody User user) {
        Assert.notNull(user, "Menu is NUll or Empty");
        user.setUserId(id);
        userService.save(user);
        return ResponseEntity.ok().build();
    }

    @Log
    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyAuthority('sys:user:delete')")
    public ResponseEntity delete(@PathVariable Long id) {
        Assert.notNull(id, "id is NUll or Empty");
        userService.removeById(id);
        return ResponseEntity.ok().build();
    }
}
