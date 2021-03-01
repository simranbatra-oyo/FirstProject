package com.example.demo1.controller;

import com.example.demo1.dto.UserDto;
import com.example.demo1.model.User;
import com.example.demo1.service.UserService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void loginUser() throws Exception{
        UserDto found=new UserDto("Rajeev Singh","abc");
        when(userService.validateUserDto(any(UserDto.class))).thenReturn(found);
        RequestBuilder request=MockMvcRequestBuilders.post("/login")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"user_name\": \"Rajeev Singh\", \"password\": \"abc\"}")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result=mockMvc.perform(request).andExpect(status().isOk()).andReturn();
        verify(userService,times(1)).validateUserDto(any(UserDto.class));
        verifyNoMoreInteractions(userService);

    }

    @Test
    void getUser() throws Exception {

        User found=new User(1L,"Rajeev Singh","abc","7623467423","rajeev@gmail.com");
        when(userService.getUserGivenUserName("Rajeev Singh")).thenReturn(found);

        RequestBuilder request= MockMvcRequestBuilders.get("/getUser").param("user_name","Rajeev Singh").accept(MediaType.APPLICATION_JSON);

        MvcResult result=mockMvc.perform(request)
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().json("{\"user_id\": 1,\"user_name\": \"Rajeev Singh\",\"password\": \"abc\",\"user_phone_no\": \"7623467423\",\"user_email_id\": \"rajeev@gmail.com\"}"))
                .andReturn();

        verify(userService,times(1)).getUserGivenUserName("Rajeev Singh");
        verifyNoMoreInteractions(userService);
    }

    @Test
    void getUserId() throws Exception{

        when(userService.getIdGivenUserName("Rajeev Singh")).thenReturn(1L);
        RequestBuilder request=MockMvcRequestBuilders.get("/get/{user_name}","Rajeev Singh").accept(MediaType.APPLICATION_JSON);
        MvcResult result=mockMvc.perform(request)
                .andExpect(status().isOk())
               // .andExpect(content().toString())
               // .andExpect(content().string("1L"))
                .andReturn();
        //String actualResponseBody = result.getResponse().getContentAsString();
        //System.out.println(actualResponseBody);

        verify(userService,times(1)).getIdGivenUserName("Rajeev Singh");
        verifyNoMoreInteractions(userService);

        /*
        .body("id", equalTo(Integer.valueOf(id.toString())));

        long expected = 1L;
        when().get("/test/{id}", expected).
        then().body("id",Long::valueOf, Matchers.is(expected));

         */
    }


}