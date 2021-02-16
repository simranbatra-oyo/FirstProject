package com.example.demo1.controller;

import com.example.demo1.dto.UserDto;
import com.example.demo1.model.User;
import com.example.demo1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:4200"})
@RestController
public class UserController {

    @Autowired
    private UserService userService;

//    @GetMapping("/login")
//    public Boolean loginUser( @RequestParam("user_id") Long user_id, @RequestParam("password") String password){
//        return userService.validateUser(user_id,password);
//    }

    @PostMapping("/login")
    public UserDto loginUser(@RequestBody UserDto userDto){
            //@RequestParam("user_name") String user_name,@RequestParam("password") String password){
        return userService.validateUserDto(userDto);
        //return userService.validateUser();
    }


    @GetMapping("/getUser")
    public User getUser(@RequestParam("user_name") String user_name){
        return userService.getUserGivenUserName(user_name);
    }

    @GetMapping("/get/{user_name}")
    public Long getUserId(@PathVariable("user_name") String user_name){
        return userService.getIdGivenUserName(user_name);
    }


//    @GetMapping("/getUser")
//    public User getUser(@RequestParam("user_id") Long user_id){
//       return userService.getUserGivenUserId(user_id);
//   }
}
