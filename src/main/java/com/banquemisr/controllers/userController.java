package com.banquemisr.controllers;

import java.util.List;
import java.util.stream.Collectors;

import com.banquemisr.DTO.UserDTO;
import com.banquemisr.entity.app_user;
import com.banquemisr.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
	@PreAuthorize("hasRole(permitAll())")
	public void register(@RequestBody UserDTO userDTO)
	{
		app_user user=userService.createNewUser(userDTO);
		UserDTO newUserDTO=modelMapper.map(user,UserDTO.class);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(value = "/getAllUsers")
	public List<UserDTO> getAllUsers()
	{
		return userService.getAllUsers().stream().map(user -> modelMapper.map(user, UserDTO.class))
				.collect(Collectors.toList());
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@GetMapping("/getById/{id}")
	@Transactional
	public ResponseEntity<UserDTO> getUserById(@PathVariable Long id){
		app_user user= userService.getUserById(id);
		UserDTO userDTO=modelMapper.map(user,UserDTO.class);

		return ResponseEntity.ok().body(userDTO);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/deleteById/{id}")
	public void deleteUser(@PathVariable Long id) {
		userService.deleteUserById(id);
		
	}
}
