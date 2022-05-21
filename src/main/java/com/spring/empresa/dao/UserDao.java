package com.spring.empresa.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.empresa.entity.User;

public interface UserDao extends JpaRepository<User, Long> {
    
    User findByUserName(String username);

}