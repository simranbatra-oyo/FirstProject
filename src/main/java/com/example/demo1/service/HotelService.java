package com.example.demo1.service;

import com.example.demo1.model.Hotel;
import com.example.demo1.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService implements IHotelService {
    @Autowired
    private HotelRepository repository;

    @Override
    public List<Hotel> getAllHotels() {
        return repository.findAll();
    }

    @Override
    public Hotel getHotelById(Long hotelId) {
        return repository.findById(hotelId).orElse(null);
    }

    public Hotel save(Hotel hotel) {
        return repository.save(hotel);
    }

}
