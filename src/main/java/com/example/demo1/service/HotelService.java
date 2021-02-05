package com.example.demo1.service;

import com.example.demo1.model.Hotel;
import com.example.demo1.repository.HotelRepository;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class HotelService implements IHotelService{
    @Autowired
    private HotelRepository repository;

    //@Autowired
    //private IHotelService repo;

    @Override
    public List<Hotel> findAll() {

        List<Hotel> hotels =  repository.findAll();

        return hotels;
    }

    @Override
    public Optional<Hotel> getHotelById(Long hotel_id) {
        //Optional<Hotel> h=repository.findById(hotel_id).orElse(null);
        //return h;
        Optional<Hotel> hotel = repository.findById(hotel_id);
        //Optional<Hotel> opt = Optional.ofNullable(user);
        return hotel;

    }

}
