package com.example.demo1.controller;

import com.example.demo1.model.Hotel;
import com.example.demo1.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HotelController {
    @Autowired
    HotelService hotelService;


    @GetMapping("/api/v1/get/allHotels")
    public List<Hotel> getHotels() {
        return hotelService.getAllHotels();
    }

    @GetMapping("/api/v1/get/hotel/{id}")
    public Hotel getHotelById(@PathVariable long id) {
        return hotelService.getHotelById(id);
    }

    @PostMapping("/api/v1/add/hotel")
    public Hotel addHotel(@RequestBody Hotel hotel) {
        return hotelService.save(hotel);
    }
}
