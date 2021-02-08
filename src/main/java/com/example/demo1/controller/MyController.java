package com.example.demo1.controller;

import com.example.demo1.model.Feedback;
import com.example.demo1.model.Hotel;
import com.example.demo1.model.User;
import com.example.demo1.repository.FeedbackRepository;
import com.example.demo1.repository.HotelRepository;
import com.example.demo1.repository.UserRepository;
import com.example.demo1.service.FeedbackService;
import com.example.demo1.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:4200"})
@RestController
public class MyController {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private HotelRepository hotelRepository;

    /*
    @Autowired
    private FeedbackService feedbackService;

     */

    @Autowired
    private FeedbackRepository feedbackRepository;


    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public String viewHomePage() {

        return "index";
    }

    @GetMapping("/registerUser")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());

        return "signup_form";
    }

    @PostMapping("/process_register")
    public String processRegister(User user) {

        userRepository.save(user);

        return "register_success";
    }

    @GetMapping("/showHotels")
    public List<Hotel> findHotels() {

        List<Hotel> hotelList = hotelService.findAll();

        return hotelList;
    }

    @GetMapping("/showHotelDetails/{hotel_id}")
    public @ResponseBody
    Optional<Hotel> findHotelsDetails(@PathVariable("hotel_id") Long hotel_id) {
        return hotelService.getHotelById(hotel_id);
    }

    @GetMapping("/showReviewForHotel/{hotel_id}")
    public List<Feedback> findReviewForHotel(@PathVariable("hotel_id") Long hotel_id) {
        return feedbackRepository.findRoomByStatus(hotel_id);
    }


    @PostMapping("/addReview/{hotel_id}/{user_id}")
    public String addReviewForHotel(@PathVariable Long user_id, @PathVariable Long hotel_id, @Param("rating") float rating, @Param("review") String review) {
        //how to get primary key and add that
        Feedback feedback = new Feedback();
        //User user = userRepository.findByUser_id(user_id);

        User user = userRepository.getOne(user_id);
        feedback.setUser(user);
        Hotel hotel = hotelRepository.getOne(hotel_id);
        feedback.setHotel(hotel);
        feedback.setRating(rating);
        feedback.setReview(review);
        feedbackRepository.save(feedback);
        return "Added new review to repo!";
    }
}
