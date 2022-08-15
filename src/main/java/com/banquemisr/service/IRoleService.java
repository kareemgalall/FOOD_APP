package com.banquemisr.service;

import com.banquemisr.entity.Role;

public interface IRoleService {
    public Role saveRole(Role role);
    public void addRoleToUser(String username, String roleName);
}
