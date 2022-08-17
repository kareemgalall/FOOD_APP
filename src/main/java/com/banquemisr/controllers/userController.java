package com.banquemisr.controllers;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import com.banquemisr.DTO.UserDTO;
import com.banquemisr.entity.app_user;
import com.banquemisr.filter.CustomAuthenticationFilter;
import com.banquemisr.repository.UserRepository;
import com.banquemisr.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;
import org.json.simple.JSONObject;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("user")
public class userController {
	@Autowired
	private UserService userService;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserRepository userRepository;

	@PostMapping("/register")
	public void register(@RequestBody UserDTO userDTO)
	{
		app_user user=userService.createNewUser(userDTO);
		//UserDTO newUserDTO=modelMapper.map(user,UserDTO.class);
	}

	@PreAuthorize("permitAll()")
	@GetMapping(value = "/getAllUsers")
	public List<UserDTO> getAllUsers()
	{
		return userService.getAllUsers().stream().map(user -> modelMapper.map(user, UserDTO.class))
				.collect(Collectors.toList());
	}

	@PreAuthorize("permitAll()")
	@GetMapping("/getById/{id}")
	@Transactional
	public ResponseEntity<UserDTO> getUserById(@PathVariable Long id){
		app_user user= userService.getUserById(id);
		UserDTO userDTO=modelMapper.map(user,UserDTO.class);
		return ResponseEntity.ok().body(userDTO);
	}

	@PreAuthorize("permitAll()")
	@DeleteMapping("/deleteById/{id}")
	public void deleteUser(@PathVariable Long id) {
		userService.deleteUserById(id);
		
	}

	@PreAuthorize("permitAll()")
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject currentUserName(Principal principal) {
		app_user user=userRepository.findByUsername(principal.getName());
		JSONObject obj=new JSONObject();
		obj.put("username",user.getUsername());
		obj.put("email",user.getEmail());
		return obj;
	}
}
