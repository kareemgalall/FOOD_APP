package com.banquemisr.repository;

import com.banquemisr.entity.app_user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<app_user,Long>  {
    app_user findByUsername(String name);
}

