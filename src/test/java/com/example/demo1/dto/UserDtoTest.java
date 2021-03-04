package com.example.demo1.dto;

import com.example.demo1.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserDtoTest {

    public static final String user_name="Rajeev Singh";
    public static final String password="abc";

    private UserDto userDto;
    private UserDto userDto1;
    private User user=new User(1L,"Rajeev Singh","abc","7623467423","rajeev@gmail.com");

    @BeforeEach
    void setUp() {
        userDto=new UserDto("Rajeev Singh","abc");
        userDto1=new UserDto();
    }

//    @AfterEach
//    void tearDown() {
//    }

    @Test
    void testUserDtoDetails() {
        assertEquals(user_name,userDto.getUser_name());
        assertEquals(password,userDto.getPassword());
    }

    @Test
    void testGetUserDto() {
        userDto1=UserDto.get(user);
        assertEquals(user.getUser_name(),userDto1.getUser_name());
        assertEquals(user.getPassword(),userDto1.getPassword());
    }

    @Test
    void builder() {
        userDto1=UserDto.builder().user_name(user_name).password(password).build();
        assertEquals(user_name,userDto1.getUser_name());
        assertEquals(password,userDto1.getPassword());
    }

//    @Test
//    void getUser_name() {
//        assertEquals(user_name,userDto.getUser_name());
//    }
//
//    @Test
//    void getPassword() {
//        assertEquals(password,userDto.getPassword());
//    }

}