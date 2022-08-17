package com.banquemisr.entity;

import javax.persistence.*;

import lombok.*;

import java.util.ArrayList;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "app_user")
@Entity
public class appUser
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "username")
	private String username;
	@Column(name = "email")
	private String email;
	@Column(name = "password")
	private String password;
	@ManyToMany(fetch = FetchType.EAGER)
	private Collection<Role> roles =new ArrayList<>();
}