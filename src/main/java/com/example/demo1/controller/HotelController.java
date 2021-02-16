package com.example.demo1.controller;

import com.example.demo1.model.Hotel;
import com.example.demo1.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:4200"})
@RestController
public class HotelController {

    @Autowired
    private HotelService hotelService;


    @GetMapping("/showHotels")
    public List<Hotel> findHotels(){
        return hotelService.findAll();
    }

    @GetMapping("/showHotelDetails/{hotel_id}")
    public Hotel findHotelsDetails(@PathVariable("hotel_id") Long hotel_id) {
        return hotelService.getHotelById(hotel_id);
    }

    //currently not using this
    @PostMapping("/add/hotel")
    public Hotel addHotel(@RequestBody Hotel hotel){
        return hotelService.save(hotel);
    }

    @GetMapping("/findHotelsByPattern/{hotel_name}")
    public List<Hotel> findHotelPattern(@PathVariable("hotel_name") String hotel_name){
        return hotelService.getHotelByPattern(hotel_name);
    }



}
