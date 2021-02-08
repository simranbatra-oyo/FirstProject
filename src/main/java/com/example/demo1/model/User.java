package com.example.demo1.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    //@GeneratedValue(strategy= GenerationType.TABLE)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long user_id;
    private String password;
    private String user_name;
    private String user_phone_no;
    private String user_email_id;


}
