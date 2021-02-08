package com.example.demo1.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "feedbacks")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long feedback_id;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    @JoinColumn(name = "hotel_id")
    @ManyToOne
    private Hotel hotel;




    //public static long idCounter=0;
    //private Long user_id;

    /*@ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    //@JsonManagedReference
    private User user;



    @ManyToOne
    @JoinColumn(name = "hotel_id", referencedColumnName = "hotel_id")
    //@JsonManagedReference
    private Hotel hotel;

     */


    //private Hotel hotel;
    //

    private float rating;
    private String review;
}
