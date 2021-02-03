package com.example.demo1.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
@Entity
@Data
//@NoArgsConstructor
//@RequiredArgsConstructor
@Table(name = "hotels")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long hotel_id;
    private String hotel_name;
    private String hotel_contact_no;
    private String hotel_address;
    private String occupancies;
    private float minimum_price;
    private float hotel_rating;

    //CREATE TABLE hotels(hotel_id INT PRIMARY KEY, hotel_name VARCHAR(20),hotel_contact_no VARCHAR(10),hotel_address VARCHAR(40),occupancies av_oc,minimum_price float,hotel_ratings float);


}
