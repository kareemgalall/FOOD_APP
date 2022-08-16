package com.banquemisr.DTO;

import com.banquemisr.entity.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO
{
    private Long id;
    private String username;
    private String email;
    private String password;
}
