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
public class app_user
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "name")
	private String name;
	@Column(name = "email")
	private String email;
	@Column(name = "password")
	private String password;
	@ManyToMany(fetch = FetchType.LAZY)
	private Collection<Role> roles =new ArrayList<>();
}

