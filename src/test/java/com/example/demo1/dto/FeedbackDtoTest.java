package com.example.demo1.dto;

import com.example.demo1.model.Feedback;
import com.example.demo1.model.Hotel;
import com.example.demo1.model.Occupancy;
import com.example.demo1.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FeedbackDtoTest {

    public static final Long hotel_id=1L;
    public static final Long user_id=1L;
    public static final float rating=4f;
    public static final String review="Located just near the fort in city(perfect location).";


    private FeedbackDto feedbackDto;
    private FeedbackDto feedbackDto1;

    private User user=new User(1L,"Rajeev Singh","abc","7623467423","rajeev@gmail.com");
    private Hotel hotel=new Hotel((long)1,"Hotel Indraprasth","8867896754","Near AIR Office, Shramik Nagar, Indore", Occupancy.SINGLE,(float) 700.0,(float) 3.0);
    private Feedback feedback=new Feedback(1L,user,hotel,(float)4,"Located just near the fort in city(perfect location).");


    @BeforeEach
    void setUp() {
        feedbackDto=new FeedbackDto(1L,1L,(float)4,"Located just near the fort in city(perfect location).");
        feedbackDto1=new FeedbackDto();
    }

    @Test
    void testFeedbackDtoDetails() {
        assertEquals(hotel_id,feedbackDto.getHotel_id());
        assertEquals(user_id,feedbackDto.getUser_id());
        assertEquals(rating,feedbackDto.getRating());
        assertEquals(review,feedbackDto.getReview());
    }

    @Test
    void testGetFeedbackDto() {
        feedbackDto1=FeedbackDto.get(feedback);

        assertEquals(hotel_id,feedbackDto1.getHotel_id());
        assertEquals(user_id,feedbackDto1.getUser_id());
        assertEquals(rating,feedbackDto1.getRating());
        assertEquals(review,feedbackDto1.getReview());
    }

    @Test
    void builder() {
        feedbackDto1=FeedbackDto.builder().hotel_id(hotel_id).user_id(user_id).rating(rating).review(review).build();

        assertEquals(hotel_id,feedbackDto1.getHotel_id());
        assertEquals(user_id,feedbackDto1.getUser_id());
        assertEquals(rating,feedbackDto1.getRating());
        assertEquals(review,feedbackDto1.getReview());
    }

//    @Test
//    void getHotel_id() {
//    }
//
//    @Test
//    void getUser_id() {
//    }
}