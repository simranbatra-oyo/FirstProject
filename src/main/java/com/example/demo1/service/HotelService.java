package com.example.demo1.service;

import com.example.demo1.model.Hotel;
import com.example.demo1.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public Hotel getHotelById(Long hotel_id) {
        Hotel hotel = repository.findById(hotel_id).orElse(null);
        return hotel;

    }

    public Hotel save(Hotel hotel){
        repository.save(hotel);
        return hotel;
    }

//    public List<Hotel> getHotelByPattern(String hotel_name){
//        List<Hotel> hotelList=new ArrayList<>();
//
//        List<Hotel> hotels =  repository.findAll();
//        for(Hotel hotel:hotels)
//        {
//            String hotelName=hotel.getHotel_name();
//            if(hotelName.matches(".*(hotel_name).*"))
//            {
//                hotelList.add(hotel);
//            }
//        }
//        return hotelList;
//
//    }

//    public List<Hotel> getHotelByPattern(String hotel_name){
//        return repository.findHotelByHotel_name(hotel_name);
//    }

//    public List<Hotel> getHotelByPattern(String hotel_name){
//        return repository.findByHotel_nameIsLike(hotel_name);
//    }
    public List<Hotel> getHotelByPattern(String hotel_name){
        hotel_name="%" + hotel_name + "%";
        return repository.searchByHotelNameLike(hotel_name);
    }



}
