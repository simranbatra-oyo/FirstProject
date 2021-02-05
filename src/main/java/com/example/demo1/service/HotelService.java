package com.example.demo1.service;

import com.example.demo1.model.Hotel;
import com.example.demo1.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService implements IHotelService{
    @Autowired
    private HotelRepository repository;

    @Override
    public List<Hotel> findAll() {

        List<Hotel> hotels =  repository.findAll();

        return hotels;
    }

    @Override
    public Optional<Hotel> getHotelById(Long hotel_id) {
        Optional<Hotel> hotel = repository.findById(hotel_id);
        return hotel;

    }

}
