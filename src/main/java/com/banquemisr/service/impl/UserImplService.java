package com.banquemisr.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.banquemisr.DTO.UserDTO;
import com.banquemisr.entity.Role;
import com.banquemisr.entity.appUser;
import com.banquemisr.repository.RoleRepository;
import com.banquemisr.repository.UserRepository;
import com.banquemisr.service.IUserService;
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
public class UserImplService implements UserDetailsService, IUserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	RoleImplService roleImplService;
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		appUser user =userRepository.findByUsername(username);
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
	@Override
	public List<appUser> getAllUsers(){
		return userRepository.findAll();
	}
	@Override
	public Optional<appUser> getUserById(Long id)
	{
		try
		{
			Optional<appUser>user=Optional.of(userRepository.getById(id));
			return user;
		}
		catch (Exception e)
		{
			return Optional.empty();
		}
		//return userRepository.getById(id);
	}
	@Override
	public appUser createNewUser(UserDTO userDTO) {
		if (userDTO != null) {
			appUser newUser=modelMapper.map(userDTO, appUser.class);
			newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
			Role role =roleRepository.findByName("ROLE_USER");
			newUser.getRoles().add(role);
			return userRepository.save(newUser);
		}
		return null;
	}
	@Override
	public Boolean deleteUserById(Long id) {
		try
		{
			Optional<appUser> user=Optional.of(userRepository.getById(id));
			if(user.isPresent()) {
				userRepository.delete(user.get());
			}
			return user.isPresent();
		}
		catch (Exception e)
		{
			return false;
		}
	}
}
