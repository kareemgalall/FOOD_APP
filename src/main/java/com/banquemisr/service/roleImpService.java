package com.banquemisr.service;

import com.banquemisr.entity.Role;
import com.banquemisr.entity.app_user;
import com.banquemisr.repository.RoleRepository;
import com.banquemisr.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class roleImpService implements IRoleService
{
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;
    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to db ",role.getName());
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role{} to user {} to db",roleName,username);
        app_user user=userRepository.findByName(username);
        Role role =roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }
}
