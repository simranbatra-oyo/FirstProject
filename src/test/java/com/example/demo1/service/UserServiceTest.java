package com.example.demo1.service;

import com.example.demo1.dto.UserDto;
import com.example.demo1.model.User;
import com.example.demo1.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@TestPropertySource(locations = {"classpath:com/example/demo1/"})
//@Configuration
//@PropertySource({"classpath:com/example/demo1/"})
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository repository;

    /*
    @Test
    void validateUserDto(){
        User found=new User(1L,"Rajeev Singh","abc","7623467423","rajeev@gmail.com");
        when(repository.findUserByUser_name("Rajeev Singh")).thenReturn(found);

        UserDto pass=UserDto.builder()
                .user_name("Rajeev Singh")
                .password("abc")
                .build();

//        UserDto pass =new UserDto("Rajeev Singh","abc");

  //      UserDto pass=UserDto.get(found);
      //System.out.println(pass.getUser_name());

       UserDto userDto=userService.validateUserDto(pass);
        assertNotNull(userDto);
        assertEquals("abc",userDto.getPassword());
    }
     */
    @Test
    void validateUserDto_whenUserIsNotNull_AndPasswordMatches(){
        UserDto returned=new UserDto("Rajeev Singh","abc");

        when(repository.findUserByUser_name(returned.getUser_name())).thenReturn(new User(1L,"Rajeev Singh","abc","7623467423","rajeev@gmail.com"));

        UserDto userDto=userService.validateUserDto(new UserDto("Rajeev Singh","abc"));

        assertNotNull(new User(1L,"Rajeev Singh","abc","7623467423","rajeev@gmail.com"));
        assertEquals("abc",userDto.getPassword());

        verify(repository,times(1)).findUserByUser_name(any(String.class));
        verifyNoMoreInteractions(repository);
    }
    @Test
    void validateUserDto_whenUserIsNotNull_AndPasswordNotMatches(){
        UserDto getUserDtoFromParameter=new UserDto("Rajeev Singh","abcd");
       // User returned=new User(1L,"Rajeev Singh","abc","7623467423","rajeev@gmail.com");

        when(repository.findUserByUser_name(getUserDtoFromParameter.getUser_name())).thenReturn(new User(1L,"Rajeev Singh","abc","7623467423","rajeev@gmail.com"));

        UserDto userDto=userService.validateUserDto(new UserDto("Rajeev Singh","abcd"));

        assertNotNull(new User(1L,"Rajeev Singh","abc","7623467423","rajeev@gmail.com"));
        assertNotEquals("abcd",new User(1L,"Rajeev Singh","abc","7623467423","rajeev@gmail.com").getPassword());
        assertNull(userDto);

        verify(repository,times(1)).findUserByUser_name(any(String.class));
        verifyNoMoreInteractions(repository);
    }
    @Test
    void validateUserDto_whenUserIsNull(){
        UserDto getUserDtoFromParameter=new UserDto("Rajeev Singh","abcd");
        // User returned=new User(1L,"Rajeev Singh","abc","7623467423","rajeev@gmail.com");

        when(repository.findUserByUser_name(getUserDtoFromParameter.getUser_name())).thenReturn(null);

        UserDto userDto=userService.validateUserDto(new UserDto("Rajeev Singh","abcd"));

        assertNull(null);
        assertNull(userDto);

        verify(repository,times(1)).findUserByUser_name(any(String.class));
        verifyNoMoreInteractions(repository);
    }


    @Test
    void validateUser_whenUserNotNull_whenPasswordMatch() {
        User returned=new User(1L,"Rajeev Singh","abc","7623467423","rajeev@gmail.com");

        when(repository.findUserByUser_name("Rajeev Singh")).thenReturn(returned);

        Boolean isUserValid=userService.validateUser("Rajeev Singh","abc");

        assertNotNull(new User(1L,"Rajeev Singh","abc","7623467423","rajeev@gmail.com"));
        assertEquals("abc",new User(1L,"Rajeev Singh","abc","7623467423","rajeev@gmail.com").getPassword());
        assertTrue(isUserValid);

        verify(repository,times(1)).findUserByUser_name(any(String.class));
        verifyNoMoreInteractions(repository);
    }
    @Test
    void validateUser_whenUserNotNull_whenPasswordNotMatch() {
        User returned=new User(1L,"Rajeev Singh","abc","7623467423","rajeev@gmail.com");

        when(repository.findUserByUser_name("Rajeev Singh")).thenReturn(returned);

        Boolean isUserValid=userService.validateUser("Rajeev Singh","abcd");

        assertNotNull(new User(1L,"Rajeev Singh","abc","7623467423","rajeev@gmail.com"));
        assertNotEquals("abcd",new User(1L,"Rajeev Singh","abc","7623467423","rajeev@gmail.com").getPassword());
        assertFalse(isUserValid);

        verify(repository,times(1)).findUserByUser_name(any(String.class));
        verifyNoMoreInteractions(repository);
    }
    @Test
    void validateUser_whenUserNull() {
        User returned=null;

        when(repository.findUserByUser_name("Rajeev Singh")).thenReturn(returned);

        Boolean isUserValid=userService.validateUser("Rajeev Singh","abcd");

        assertNull(null);
        assertFalse(isUserValid);

        verify(repository,times(1)).findUserByUser_name(any(String.class));
        verifyNoMoreInteractions(repository);
    }


    @Test
    void getIdGivenUserName_whenUserIsNotNull() {
        User returned=new User(1L,"Rajeev Singh","abc","7623467423","rajeev@gmail.com");

        when(repository.findUserByUser_name("Rajeev Singh")).thenReturn(returned);

        Long userID=userService.getIdGivenUserName("Rajeev Singh");

        assertNotNull(new User(1L,"Rajeev Singh","abc","7623467423","rajeev@gmail.com"));
        assertEquals(1L,userID);

        verify(repository,times(1)).findUserByUser_name(any(String.class));
        verifyNoMoreInteractions(repository);
    }
    @Test
    void getIdGivenUserName_whenUserIsNull() {
       // User returned=new User();
        User returned=null;

        when(repository.findUserByUser_name("Rajeev Singh")).thenReturn(returned);

        Long userID=userService.getIdGivenUserName("Rajeev Singh");

       // assertNull(new User());
        assertNull(null);
        assertEquals(null,userID);

        verify(repository,times(1)).findUserByUser_name(any(String.class));
        verifyNoMoreInteractions(repository);
    }


    @Test
    void getUserGivenUserName() {

        User returned=new User(1L,"Rajeev Singh","abc","7623467423","rajeev@gmail.com");

        when(repository.findUserByUser_name("Rajeev Singh")).thenReturn(returned);

        User user =userService.getUserGivenUserName("Rajeev Singh");

        assertEquals(new User(1L,"Rajeev Singh","abc","7623467423","rajeev@gmail.com"),user);

        verify(repository,times(1)).findUserByUser_name(any(String.class));
        verifyNoMoreInteractions(repository);
    }

    @Test
    void getUserGivenUserId() {

       User returned=new User(1L,"Rajeev Singh","abc","7623467423","rajeev@gmail.com");

        when(repository.getOne(1L)).thenReturn(returned);

        User user = userService.getUserGivenUserId(1L);

        assertEquals(new User(1L,"Rajeev Singh","abc","7623467423","rajeev@gmail.com"),user);

        verify(repository,times(1)).getOne(1L);
        verifyNoMoreInteractions(repository);

    }

}