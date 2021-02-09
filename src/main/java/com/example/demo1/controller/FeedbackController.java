package com.example.demo1.controller;

import com.example.demo1.dto.FeedbackDto;
import com.example.demo1.model.Feedback;
import com.example.demo1.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FeedbackController {

    @Autowired
    FeedbackService feedbackService;

    @GetMapping("api/v1/get/feedback/for/hotel/{id}")
    public List<FeedbackDto> getFeedbackForHotelWithId(@PathVariable long id) {
        return feedbackService.getFeedbackForHotel(id);
    }

    @PostMapping("api/v1/get/feedback/for/hotel")
    public FeedbackDto addFeedbackForHotelWithId (@RequestBody FeedbackDto feedbackDto) throws Exception{
        return feedbackService.addFeedback(feedbackDto);
    }
}
