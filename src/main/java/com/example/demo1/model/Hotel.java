package com.example.demo1.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Data
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
    private float hotel_ratings;

    //CREATE TABLE hotels(hotel_id INT PRIMARY KEY, hotel_name VARCHAR(20),hotel_contact_no VARCHAR(10),hotel_address VARCHAR(40),occupancies av_oc,minimum_price float,hotel_ratings float);


}
