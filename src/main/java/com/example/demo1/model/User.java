package com.example.demo1.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "users")
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long user_id;

    @Column(name="user_name")
    private String user_name;

    @Column(name="password")
    private String password;

    @Column(name="user_phone_no")
    private String user_phone_no;

    @Column(name = "user_email_id")
    private String user_email_id;


}
