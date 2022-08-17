package com.banquemisr.repository;

import com.banquemisr.entity.appUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<appUser,Long>  {
    appUser findByUsername(String name);
}

