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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public User(){

    }

    public User(Long user_id, String user_name, String password, String user_phone_no, String user_email_id) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.password = password;
        this.user_phone_no = user_phone_no;
        this.user_email_id = user_email_id;
    }
}
