package com.banquemisr.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.banquemisr.DTO.UserDTO;
import com.banquemisr.entity.appUser;
import com.banquemisr.repository.UserRepository;
import com.banquemisr.service.impl.UserImplService;
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
	private UserImplService userService;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserRepository userRepository;

	@PostMapping("/register")
	public void register(@RequestBody UserDTO userDTO)
	{
		appUser user=userService.createNewUser(userDTO);
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
	public ResponseEntity<?> getUserById(@PathVariable Long id){
		Optional<appUser> user= userService.getUserById(id);
		if (user.isPresent())
		{
			UserDTO userDTO=new UserDTO();
			modelMapper.map(user.get(),userDTO);
			return ResponseEntity.ok().body(userDTO);
		}
		return ResponseEntity.badRequest().build();
	}

	@PreAuthorize("permitAll()")
	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		if(userService.deleteUserById(id))
		{
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();
	}

	@PreAuthorize("permitAll()")
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject currentUserName(Principal principal) {
		appUser user=userRepository.findByUsername(principal.getName());
		JSONObject obj=new JSONObject();
		obj.put("username",user.getUsername());
		obj.put("email",user.getEmail());
		return obj;
	}
}
