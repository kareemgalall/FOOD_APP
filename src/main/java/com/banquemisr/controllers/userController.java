package com.banquemisr.controllers;

import java.util.List;
import java.util.stream.Collectors;

import com.banquemisr.DTO.UserDTO;
import com.banquemisr.entity.app_user;
import com.banquemisr.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("user")
public class userController {
	@Autowired
	private UserService userService;
	@Autowired
	private ModelMapper modelMapper;
	@PostMapping("/register")
	public void addNewUser(@RequestBody UserDTO userDTO)
	{
		app_user user=userService.createNewUser(userDTO);
		UserDTO newUserDTO=modelMapper.map(user,UserDTO.class);
	}

	@GetMapping(value = "/getAllUsers")
	public List<UserDTO> getAllUsers()
	{
		return userService.getAllUsers().stream().map(user -> modelMapper.map(user, UserDTO.class))
				.collect(Collectors.toList());
	}

	@GetMapping("/getById/{id}")
	@Transactional
	public ResponseEntity<UserDTO> getUserById(@PathVariable Long id){
		app_user user= userService.getUserById(id);
		UserDTO userDTO=modelMapper.map(user,UserDTO.class);
		return ResponseEntity.ok().body(userDTO);
	}

	@DeleteMapping("/deleteById/{id}")
	public void deleteUser(@PathVariable Long id) {
		userService.deleteUserById(id);
		
	}
}
