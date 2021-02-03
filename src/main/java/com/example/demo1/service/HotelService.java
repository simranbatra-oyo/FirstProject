package com.example.demo1.service;

import com.example.demo1.model.Hotel;
import com.example.demo1.repository.HotelRepository;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        var hotels = (List<Hotel>) repository.findAll();

        return hotels;
    }

    @Override
    public Hotel getHotelById(Long hotel_id) {
        /*

        for( Hotel hotel:)
        {
            if(hotel.getHotel_id()==hotel_id)
                return hotel;
        }
        return null;

         */
        //return repository.findById(hotel_id);
        return Optional.ofNullable(repository.findById(hotel_id)).orElse(null);
        //.orElseThrow(() -> new FlightNotFoundException(flightId));
    }

}
