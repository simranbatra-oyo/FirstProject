package com.example.demo1.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Data
//@NoArgsConstructor
//@RequiredArgsConstructor
@Table(name = "feedbacks")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long feedback_id;
    private Long user_id;
    private Long hotel_id;
    private float rating;
    private String review;

    //CREATE TABLE feedbacks(feedback_id INT PRIMARY KEY,user_id INT,FOREIGN KEY (user_id) REFERENCES users(user_id),hotel_id INT,FOREIGN KEY (hotel_id) REFERENCES hotels(hotel_id),rating float, review VARCHAR(100));
}
