package com.example.demo1.model;

import com.example.demo1.dto.FeedbackDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class FeedbackTest {

    public static final Long feedback_id=1L;
    public static final User user=new User(1L,"Rajeev Singh","abc","7623467423","rajeev@gmail.com");
    public static final Hotel hotel=new Hotel((long)1,"Hotel Indraprasth","8867896754","Near AIR Office, Shramik Nagar, Indore", Occupancy.SINGLE,(float) 700.0,(float) 3.0);
    public static final float rating=4f;
    public static final String review="Located just near the fort in city(perfect location).";
    public static final FeedbackDto feedbackDto=new FeedbackDto(1L,1L,(float)4,"Located just near the fort in city(perfect location).");


    private Feedback feedback;

//    @BeforeEach
//    void setUp() {
//        feedback=new Feedback(1L,user,hotel,(float)4,"Located just near the fort in city(perfect location).");
//    }

    @Test
    void testHotelDetails() {
        feedback=new Feedback(1L,user,hotel,(float)4,"Located just near the fort in city(perfect location).");

        assertEquals(feedback_id,feedback.getFeedback_id());
        assertEquals(user,feedback.getUser());
        assertEquals(hotel,feedback.getHotel());
        assertEquals(rating,feedback.getRating());
        assertEquals(review,feedback.getReview());
    }

    @Test
    void testGetFeedback(){
        feedback=new Feedback();

        Feedback newFeedback=Feedback.get(feedbackDto,hotel,user);

        assertEquals(hotel,newFeedback.getHotel());
        assertEquals(user,newFeedback.getUser());
        assertEquals(rating,newFeedback.getRating());
        assertEquals(review,newFeedback.getReview());
    }
}