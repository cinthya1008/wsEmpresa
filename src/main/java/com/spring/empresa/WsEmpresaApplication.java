package com.spring.empresa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.spring.empresa.dao.UserDao;
import com.spring.empresa.entity.User;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class WsEmpresaApplication {
    
    @Autowired
    private UserDao userDao;

    @PostConstruct
    public void initUsers() {
        List<User> users = Stream.of(
                new User(101, "userAdmin", "password"),
                new User(102, "user1", "pwd1"),
                new User(103, "user2", "pwd2"),
                new User(104, "user3", "pwd3")
        ).collect(Collectors.toList());
        userDao.saveAll(users);
    }

    public static void main(String[] args) {
        SpringApplication.run(WsEmpresaApplication.class, args);
    }

}
