package com.example.demo1.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserTest {

    public static final Long user_id=1L;
    public static final String user_name="Rajeev Singh";
    public static final String password="abc";
    public static final String user_phone_no="7623467423";
    public static final String user_email_id="rajeev@gmail.com";

    private User user;
    private User user1;

    @BeforeEach
    void setUp() {
        user=new User(1L,"Rajeev Singh","abc","7623467423","rajeev@gmail.com");
        user1=new User();
    }

    @Test
    void testUserDetails() {
        assertEquals(user_id,user.getUser_id());
        assertEquals(user_name,user.getUser_name());
        assertEquals(password,user.getPassword());
        assertEquals(user_phone_no,user.getUser_phone_no());
        assertEquals(user_email_id,user.getUser_email_id());
    }

}