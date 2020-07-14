package com.ponking.system.controller;


import com.ponking.system.aop.Log;
import com.ponking.system.dao.MenuDao;
import com.ponking.system.entity.Menu;
import com.ponking.system.service.MenuService;
import io.jsonwebtoken.lang.Assert;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 菜单管理 前端控制器
 * </p>
 *
 * @author peng
 * @since 2020-07-13
 */
@RestController
@RequestMapping("/api/sys/menu")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @Log
    @GetMapping
    @PreAuthorize("hasAnyAuthority('sys:menu:menu')")
    public ResponseEntity list(){
        return ResponseEntity.ok().body(menuService.list());
    }

    @Log
    @PostMapping
    @PreAuthorize("hasAnyAuthority('sys:menu:add')")
    public ResponseEntity save(@RequestBody Menu menu){
        Assert.notNull(menu,"Menu is NUll or Empty");
        menuService.save(menu);
        return ResponseEntity.ok().build();
    }

    @Log
    @PutMapping("{id}")
    @PreAuthorize("hasAnyAuthority('sys:menu:update')")
    public ResponseEntity update(@PathVariable Integer id,@RequestBody Menu menu){
        Assert.notNull(menu,"Menu is NUll or Empty");
        menu.setMenuId(id);
        menuService.save(menu);
        return ResponseEntity.ok().build();
    }

    @Log
    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyAuthority('sys:menu:delete')")
    public ResponseEntity delete(@PathVariable Long id){
        Assert.notNull(id,"id is NUll or Empty");
        menuService.removeById(id);
        return ResponseEntity.ok().build();
    }
}
