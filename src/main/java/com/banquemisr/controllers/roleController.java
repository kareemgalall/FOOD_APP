//package com.banquemisr.controllers;
//
//import com.banquemisr.entity.Role;
//import com.banquemisr.service.RoleImplService;
//import lombok.Data;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
//
//import java.net.URI;
//
//@RestController
//@RequestMapping("role")
//public class roleController {
//    @Autowired
//    RoleImplService roleService;
//    @PreAuthorize("permitAll()")
//    @PostMapping("/add")
//    public ResponseEntity<Role> addRole(@RequestBody Role role)
//    {
//        URI uri= URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/role/save").toUriString());
//        return ResponseEntity.created(uri).body(roleService.saveRole(role));
//    }
//    @PreAuthorize("permitAll()")
//    @PostMapping("/assignToUser")
//    public void assignRoleToUser(@RequestBody RoleToUser roleToUser)
//    {
//        roleService.addRoleToUser(roleToUser.getUsername(),roleToUser.getRolename());
//    }
//}
//@Data
//class RoleToUser
//{
//    private String username;
//    private String rolename;
//}