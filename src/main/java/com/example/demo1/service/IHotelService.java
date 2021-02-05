package com.example.demo1.service;

import com.example.demo1.model.Hotel;

import java.util.List;
import java.util.Optional;

public interface IHotelService {
    List<Hotel> findAll();

    Optional<Hotel> getHotelById(Long hotel_id);
}
