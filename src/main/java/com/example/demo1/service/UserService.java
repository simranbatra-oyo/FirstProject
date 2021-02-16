package com.example.demo1.service;

import com.example.demo1.dto.FeedbackDto;
import com.example.demo1.dto.UserDto;
import com.example.demo1.model.Feedback;
import com.example.demo1.model.User;
import com.example.demo1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;



    public UserDto validateUserDto(UserDto userDto){

        String userInputUsername=userDto.getUser_name();
        //String userInputPassword=userDto.getPassword();

        User user=repository.findUserByUser_name(userInputUsername);
        if(user!=null)
        {
            String userInputPassword=userDto.getPassword();
            if(userInputPassword.equals(user.getPassword()))
                return userDto;
            else
                return null;
        }
        return null;

    }

    public Boolean validateUser(String user_name,String password){
        User user=repository.findUserByUser_name(user_name);
//    public Boolean validateUser(Long user_id,String password){
//        User user=repository.getOne(user_id);
        if(user!=null && password.equals(user.getPassword()))
        {
            return true;
        }
        return false;

//        Long id=user.getUser_id();
//        List<User>userList= repository.findAll();
//        for(User n:userList)
//        {
//            if(n.getUser_id()==id)
//            {
//                return n;
//            }
//        }
//        return null;
    }

    public Long getIdGivenUserName(String user_name){
        User user=repository.findUserByUser_name(user_name);
        if(user!=null){
            return user.getUser_id();
        }
        return null;
    }

    public User getUserGivenUserName(String user_name){
        User user=repository.findUserByUser_name(user_name);
        return user;
    }

    public User getUserGivenUserId(Long user_id){
        User user=repository.getOne(user_id);
        return user;
    }


}
