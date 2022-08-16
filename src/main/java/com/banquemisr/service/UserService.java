package com.banquemisr.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.banquemisr.DTO.UserDTO;
import com.banquemisr.entity.app_user;
import com.banquemisr.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import javax.transaction.Transactional;

@Slf4j
@Service
public class UserService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	RoleImplService roleImplService;
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		app_user user =userRepository.findByUsername(username);
		if(user==null)
		{
			log.error("user not found");
			throw new UsernameNotFoundException("user not found");
		}
		else
		{
			log.info("user found");
		}

		Collection<SimpleGrantedAuthority> authorities=new ArrayList<>();
		user.getRoles().forEach(role->
		{
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		});

		return new org.springframework.security.core.userdetails.User(user.getUsername(),
				user.getPassword(),authorities);
	}

	public List<app_user> getAllUsers(){
		return userRepository.findAll();
	}
	
	public app_user getUserById(Long id)
	{
		System.out.println(id);
		return userRepository.getById(id);
	}

	public app_user createNewUser(UserDTO userDTO) {
		if (userDTO != null) {
			app_user newUser=modelMapper.map(userDTO, app_user.class);
			newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
			roleImplService.addRoleToUser(newUser.getUsername(),"ROLE_USER");
			return userRepository.save(newUser);
		}
		return null;
	}
	public void deleteUserById(Long id) {
		userRepository.deleteById(id);
	}
}
