package com.example.demo1.model;

import com.example.demo1.dto.FeedbackDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.extern.java.Log;

import javax.persistence.*;

@Entity
@Log
@Data
@Table(name = "feedbacks")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "feedback_id")
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

    @Column(name = "rating")
    private float rating;

    @Column(name = "review")
    private String review;

    public Feedback(){
    }

//    public Feedback( User user, Hotel hotel, float rating, String review) {
//        //this.feedback_id = feedback_id;
//        this.user = user;
//        this.hotel = hotel;
//        this.rating = rating;
//        this.review = review;
//    }

    public Feedback(Long feedback_id, User user, Hotel hotel, float rating, String review) {
        this.feedback_id = feedback_id;
        this.user = user;
        this.hotel = hotel;
        this.rating = rating;
        this.review = review;
    }

    public static Feedback get(FeedbackDto feedbackDto, Hotel hotel, User user) {
        Feedback feedback = new Feedback();
        feedback.setHotel(hotel);
        feedback.setUser(user);
        feedback.setRating(feedbackDto.getRating());
        feedback.setReview(feedbackDto.getReview());
        return feedback;
    }
}
