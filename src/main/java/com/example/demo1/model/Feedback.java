package com.example.demo1.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import com.example.demo1.model.User;
import com.example.demo1.model.Hotel;

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
    //@PrimaryKey(autoGenerate = true)

    //public static long idCounter=0;
    private Long user_id;

    /*@JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @JsonManagedReference
    private User user;
    @JoinColumn(name = "hotel_id", referencedColumnName = "hotel_id")
    @JsonManagedReference

     */
    //private Hotel hotel;
    //
     private Long hotel_id;
    private float rating;
    private String review;
/*
    public Feedback() {
        ++idCounter;
    }

 */
    //CREATE TABLE feedbacks(feedback_id INT PRIMARY KEY,user_id INT,FOREIGN KEY (user_id) REFERENCES users(user_id),hotel_id INT,FOREIGN KEY (hotel_id) REFERENCES hotels(hotel_id),rating float, review VARCHAR(100));
}
