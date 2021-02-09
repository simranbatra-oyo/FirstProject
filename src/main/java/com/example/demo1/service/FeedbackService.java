package com.example.demo1.service;

import com.example.demo1.dto.FeedbackDto;
import com.example.demo1.model.Feedback;
import com.example.demo1.model.Hotel;
import com.example.demo1.model.User;
import com.example.demo1.repository.FeedbackRepository;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FeedbackService implements IFeedbackService {
    @Autowired
    private FeedbackRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private HotelService hotelService;

    @Override
    public List<Feedback> findAll() {

        List<Feedback> feedbacks = repository.findAll();

        return feedbacks;
    }

    public List<FeedbackDto> getFeedbackForHotel(long hotelId) {
        List<FeedbackDto> feedbackDtoList = new ArrayList<>();
        for(Feedback feedback: repository.findFeedbacksByHotelId(hotelId)) {
            feedbackDtoList.add(FeedbackDto.get(feedback));
        }
        return feedbackDtoList;
    }

    public FeedbackDto addFeedback(FeedbackDto feedbackDto) throws Exception{
        User user = userService.getUser(feedbackDto.getUserId());
        Hotel hotel = hotelService.getHotelById(feedbackDto.getHotelId());

        if(user != null && hotel != null) {
            Feedback feedback = Feedback.get(feedbackDto, hotel, user);
            repository.save(feedback);
        } else {
            throw new Exception("Bad Request");
        }
        return feedbackDto;
    }
}
