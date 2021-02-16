package com.example.demo1.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "hotels")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "hotel_id")
    private Long hotel_id;

    @Column(name="hotel_name")
    private String hotel_name;

    @Column(name = "hotel_contact_no")
    private String hotel_contact_no;

    @Column(name = "hotel_address")
    private String hotel_address;

    @Column(name = "occupancies")
    @Enumerated(EnumType.STRING)
    private Occupancy occupancies;

    @Column(name = "minimum_price")
    private float minimum_price;

    @Column(name = "hotel_ratings")
    private float hotel_ratings;


}
