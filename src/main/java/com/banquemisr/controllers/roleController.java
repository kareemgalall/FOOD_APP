package com.banquemisr.controllers;

import com.banquemisr.entity.Role;
import com.banquemisr.service.roleImpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/role")
public class roleController {
    @Autowired
    roleImpService roleService;
    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role)
    {
        URI uri= URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/role/save").toUriString());
        return ResponseEntity.created(uri).body(roleService.saveRole(role));
    }
    @PostMapping("/role/addToUser")
    public ResponseEntity<?>assignRoleToUser(@RequestBody String name,String roleName)
    {
        roleService.addRoleToUser(name,roleName);
        return ResponseEntity.ok().build();
    }
}
