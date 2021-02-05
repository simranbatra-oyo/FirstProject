package com.example.demo1.service;

import com.example.demo1.model.Feedback;
import com.example.demo1.model.Hotel;

import java.util.List;
import java.util.Optional;

public interface IFeedbackService {
    List<Feedback> findAll();

    // Optional<List<Feedback>> getReviewById(Long hotel_id);

}
