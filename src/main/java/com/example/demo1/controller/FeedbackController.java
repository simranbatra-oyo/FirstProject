package com.example.demo1.controller;

import com.example.demo1.dto.FeedbackDto;
import com.example.demo1.model.Feedback;
import com.example.demo1.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:4200"})
@RestController
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("/showReviewForHotel/{hotel_id}")
    public List<FeedbackDto> findReviewForHotel(@PathVariable("hotel_id") Long hotel_id){
        return feedbackService.getFeedbackForHotel(hotel_id);
    }

    @PostMapping("/addReview/{hotel_id}/{user_id}")
    public FeedbackDto addReviewForHotel(@RequestBody FeedbackDto feedbackDto) throws Exception {
//            @PathVariable Long user_id, @PathVariable Long hotel_id,
//                                                         @RequestBody FeedbackDto feedbackDto) throws Exception {

        return feedbackService.addFeedback(feedbackDto);
    }

}
