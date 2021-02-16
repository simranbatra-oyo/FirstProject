package com.example.demo1.repository;

import com.example.demo1.model.Feedback;
import com.example.demo1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //User findByUser_id(long user_id);
    //User findByUser_id(Long user_id);

   // User findFirstByUsername(String username);
    //User findUserByUser_name(String user_name);


    @Query(value = "SELECT * FROM users  u WHERE u.user_name = ?1", nativeQuery = true)
    User findUserByUser_name(String user_name);

    //User findFirstByUser_nameIs( String user_name);

    //User findUserByUser_name(String user_name);



}
