package com.example.demo1.service;

import com.example.demo1.model.Feedback;
import com.example.demo1.model.Hotel;
import com.example.demo1.repository.FeedbackRepository;
import com.example.demo1.repository.HotelRepository;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService implements IFeedbackService{
    @Autowired
    private FeedbackRepository repository;

    @Override
    public List<Feedback> findAll() {

        var feedbacks = (List<Feedback>) repository.findAll();

        return feedbacks;
    }

    /*

    @Override
    public Optional<List<Feedback>> getReviewById(Long hotel_id) {
        //Optional<Hotel> h=repository.findById(hotel_id).orElse(null);
        //return h;
        Optional<List<Feedback>> feedback = (Optional<List<Feedback>>) repository.findById(hotel_id);
        //Optional<Hotel> opt = Optional.ofNullable(user);
        return feedback;
    }

    */

}
