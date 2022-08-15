package com.banquemisr.service;

import java.util.List;

import com.banquemisr.DTO.UserDTO;
import com.banquemisr.entity.app_user;
import com.banquemisr.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	private UserRepository userRep;
	@Autowired
	ModelMapper modelMapper;
	public List<app_user> getAllUsers(){
		return userRep.findAll();
	}
	
	public app_user getUserById(Long id)
	{
		System.out.println(id);
		return userRep.getById(id);
	}

	public app_user createNewUser(UserDTO userDTO) {
		if (userDTO != null) {
			app_user newUser=modelMapper.map(userDTO, app_user.class);
			return userRep.save(newUser);
		}
		return null;
	}
	public void deleteUserById(Long id) {
		userRep.deleteById(id);
	}
	
}
