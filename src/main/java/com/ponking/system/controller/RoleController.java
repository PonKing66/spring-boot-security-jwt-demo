package com.ponking.system.controller;


import com.ponking.system.aop.Log;
import com.ponking.system.entity.Role;
import com.ponking.system.service.RoleService;
import io.jsonwebtoken.lang.Assert;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 角色 前端控制器
 * </p>
 *
 * @author peng
 * @since 2020-07-13
 */
@RestController
@RequestMapping("/api/sys/role")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @Log
    @GetMapping
    @PreAuthorize("hasAnyAuthority('sys:role:list')")
    public ResponseEntity list() {
        return ResponseEntity.ok().body(roleService.list());
    }

    @Log
    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyAuthority('sys:role:delete')")
    public ResponseEntity delete(@PathVariable Long id) {
        Assert.notNull(id, "id is NUll or Empty");
        roleService.removeById(id);
        return ResponseEntity.ok().build();
    }

    @Log
    @PostMapping
    @PreAuthorize("hasAnyAuthority('sys:role:add')")
    public ResponseEntity save(@RequestBody Role role) {
        Assert.notNull(role, "Menu is NUll or Empty");
        roleService.save(role);
        return ResponseEntity.ok().build();
    }

    @Log
    @PostMapping("{id}")
    @PreAuthorize("hasAnyAuthority('sys:role:assign:menu')")
    public ResponseEntity assignMenu(@PathVariable("id")Integer roleId,@RequestBody List<Integer> menuIds) {
        //todo
        return ResponseEntity.ok().build();
    }

    @Log
    @GetMapping("{id}")
    @PreAuthorize("hasAnyAuthority('sys:role:list')")
    public ResponseEntity getOne(@PathVariable Integer id) {
        Assert.notNull(id, "id is NUll or Empty");
        Role role = roleService.getById(id);
        return ResponseEntity.ok().body(role);
    }

    @Log
    @PutMapping("{id}")
    @PreAuthorize("hasAnyAuthority('sys:role:update')")
    public ResponseEntity update(@PathVariable Integer id, @RequestBody Role role) {
        Assert.notNull(role, "Menu is NUll or Empty");
        role.setRoleId(id);
        roleService.save(role);
        return ResponseEntity.ok().build();
    }

}
