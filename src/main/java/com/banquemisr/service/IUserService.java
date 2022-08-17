package com.banquemisr.service;

import com.banquemisr.DTO.UserDTO;
import com.banquemisr.entity.appUser;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    public List<appUser> getAllUsers();
    public Optional<appUser> getUserById(Long id);
    public appUser createNewUser(UserDTO userDTO);
    public Boolean deleteUserById(Long id);
}
