package com.example.demo1.service;

import com.example.demo1.model.Hotel;

import java.util.List;
import java.util.Optional;

interface IHotelService {
    List<Hotel> getAllHotels();

    Hotel getHotelById(Long hotel_id);
}
