package com.example.demo1.model;

import com.example.demo1.dto.FeedbackDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "feedback_id")
    private Long feedbackId;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    @JoinColumn(name = "hotel_id")
    @ManyToOne
    private Hotel hotel;

    @Column(name = "rating")
    private float rating;

    @Column(name = "review")
    private String review;

    public static Feedback get(FeedbackDto feedbackDto, Hotel hotel, User user) {
        Feedback feedback = new Feedback();
        feedback.setHotel(hotel);
        feedback.setUser(user);
        feedback.setRating(feedbackDto.getRating());
        feedback.setReview(feedbackDto.getReview());
        return feedback;
    }
}
