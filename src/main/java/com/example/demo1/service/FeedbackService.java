package com.example.demo1.service;

import com.example.demo1.dto.FeedbackDto;
import com.example.demo1.model.Feedback;
import com.example.demo1.model.Hotel;
import com.example.demo1.model.User;
import com.example.demo1.repository.FeedbackRepository;
import com.example.demo1.repository.HotelRepository;
import com.example.demo1.repository.UserRepository;
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
    private HotelService hotelService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

//    @Override
//    public List<Feedback> findAll() {
//        List<Feedback> feedbacks = repository.findAll();
//        return feedbacks;
//    }

    @Override
    public List<FeedbackDto> findAll(){
        List<FeedbackDto> feedbackDtoList=new ArrayList<>();
        List<Feedback> feedbackList=repository.findAll();
        for(Feedback feedback:feedbackList)
        {
            feedbackDtoList.add(FeedbackDto.get(feedback));
        }
        return feedbackDtoList;
    }

    public List<FeedbackDto> getFeedbackForHotel(Long hotel_id){
        List<FeedbackDto> feedbackDtoList=new ArrayList<>();
        for(Feedback feedback:repository.findFeedbacksByHotelId(hotel_id))
        {
            feedbackDtoList.add(FeedbackDto.get(feedback));
        }
        return feedbackDtoList;
    }

    public FeedbackDto addFeedback(FeedbackDto feedbackDto) throws Exception{
       // Feedback feedback = new Feedback();

        Long idHotel = feedbackDto.getHotel_id();
        Hotel hotel = hotelService.getHotelById(idHotel);

        Long idUser = feedbackDto.getUser_id();
        //User user=userRepository.getOne(idUser);
        User user=userService.getUserGivenUserId(idUser);

        //float rating = feedbackDto.getRating();

        String review = feedbackDto.getReview();


        if(hotel !=null && user!=null && review!=null){
//            feedback.setHotel(hotel);
//            feedback.setUser(user);
//            feedback.setRating(rating);
//            feedback.setReview(review);
            Feedback feedback = Feedback.get(feedbackDto, hotel, user);

            repository.save(feedback);

        }
        else {
            throw new Exception("Bad Request");
        }
        return feedbackDto;
    }

}
