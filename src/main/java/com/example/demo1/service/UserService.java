package com.example.demo1.service;

import com.example.demo1.model.User;
import com.example.demo1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    public boolean validateUser(String userName, String password) {
        User user = repository.findFirstByUserName(userName);
        if(user != null && password.equals(user.getPassword())) {
            return true;
        }
        return false;
    }

    public User getUser(String userName) {
        User user = repository.findFirstByUserName(userName);
        if(user != null) {
            user.setPassword("");
        }
        return user;
    }

    public User getUser(long userId) {
        return repository.getOne(userId);
    }
}
