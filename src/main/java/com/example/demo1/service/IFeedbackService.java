package com.example.demo1.service;

import com.example.demo1.model.Feedback;

import java.util.List;

public interface IFeedbackService {
    List<Feedback> findAll();
}
