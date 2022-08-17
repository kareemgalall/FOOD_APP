package com.banquemisr.service.impl;

import com.banquemisr.entity.Role;
import com.banquemisr.entity.appUser;
import com.banquemisr.repository.RoleRepository;
import com.banquemisr.repository.UserRepository;
import com.banquemisr.service.IRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Transactional
@Service
public class RoleImplService implements IRoleService
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
        appUser user=userRepository.findByUsername(username);
        Role role =roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }
}
