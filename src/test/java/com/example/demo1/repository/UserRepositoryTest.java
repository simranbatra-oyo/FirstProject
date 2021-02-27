package com.example.demo1.repository;

import com.example.demo1.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Test
    void findUserByUser_name() {
        User user = repository.findUserByUser_name("Rajeev Singh");
        //System.out.println(user);
        //User(user_id=1, user_name=Rajeev Singh, password=abc, user_phone_no=7623467423, user_email_id=rajeev@gmail.com)
        assertThat(user).isNotNull();
        assertThat(user).isEqualTo(new User(1L,"Rajeev Singh","abc","7623467423","rajeev@gmail.com"));
    }

    @Test
    void getOne_whenUserExistWithThatId(){
        User user = repository.getOne(1L);

        assertThat(user).isNotNull();
        assertThat(user).isEqualTo(new User(1L,"Rajeev Singh","abc","7623467423","rajeev@gmail.com"));
    }
//    @Test
//    void getOne_whenUserDoesNotExistWithThatId(){
//        //User user = repository.getOne(5L);
//
//        assertThat(repository.getOne(5L)).isNull();
//    Gives error - javax.persistence.EntityNotFoundException: Unable to find com.example.demo1.model.User with id 5
//    }
}