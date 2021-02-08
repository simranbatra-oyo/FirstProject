package com.example.demo1.repository;

import com.example.demo1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //User findByUser_id(long user_id);
    //User findByUser_id(Long user_id);
}
