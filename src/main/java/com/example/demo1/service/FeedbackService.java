package com.example.demo1.service;

import com.example.demo1.model.Feedback;
import com.example.demo1.model.Hotel;
import com.example.demo1.repository.FeedbackRepository;
import com.example.demo1.repository.HotelRepository;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService implements IFeedbackService{
    @Autowired
    private FeedbackRepository repository;

    @Override
    public List<Feedback> findAll() {

        var feedbacks = (List<Feedback>) repository.findAll();

        return feedbacks;
    }

}
