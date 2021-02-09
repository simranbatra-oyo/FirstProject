package com.example.demo1.controller;

import com.example.demo1.model.User;
import com.example.demo1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("api/v1/validate/user")
    public Boolean validateUser(@RequestParam("user_id") String userId, @RequestParam("password") String password) {
        return userService.validateUser(userId, password);
    }

    @GetMapping("api/v1/get/user")
    public User validateUser(@RequestParam("user_id") String userId) {
        return userService.getUser(userId);
    }
}
