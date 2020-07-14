package com.ponking.system.controller;


import com.ponking.system.aop.Log;
import com.ponking.system.entity.Role;
import com.ponking.system.service.RoleService;
import io.jsonwebtoken.lang.Assert;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    @PreAuthorize("hasAnyAuthority('ROLE_ROLE')")
    public ResponseEntity list() {
        return ResponseEntity.ok().body(roleService.list());
    }

    @Log
    @PostMapping
    @PreAuthorize("hasAnyAuthority('ROLE')")
    public ResponseEntity save(@RequestBody Role role) {
        Assert.notNull(role, "Menu is NUll or Empty");
        roleService.save(role);
        return ResponseEntity.ok().build();
    }

    @Log
    @PreAuthorize("hasAnyAuthority('ROLE')")
    @GetMapping("{id}")
    public ResponseEntity getOne(@PathVariable Integer id) {
        Assert.notNull(id, "id is NUll or Empty");
        Role role = roleService.getById(id);
        return ResponseEntity.ok().body(role);
    }

    @Log
    @PutMapping("{id}")
    @PreAuthorize("hasAnyRole('ROLE_ROLE')")
    public ResponseEntity update(@PathVariable Integer id, @RequestBody Role role) {
        Assert.notNull(role, "Menu is NUll or Empty");
        role.setRoleId(id);
        roleService.save(role);
        return ResponseEntity.ok().build();
    }

    @Log
    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyRole('ROLE_ROLE')")
    public ResponseEntity delete(@PathVariable Long id) {
        Assert.notNull(id, "id is NUll or Empty");
        roleService.removeById(id);
        return ResponseEntity.ok().build();
    }
}
