package com.example.demo1.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "hotels")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "hotel_id")
    private Long hotelId;

    @Column(name = "hotel_name")
    private String hotelName;

    @Column(name = "hotel_contact_no")
    private String hotelContactNo;

    @Column(name = "hotel_address")
    private String hotelAddress;

    @Column(name = "occupancies")
    @Enumerated(EnumType.STRING)
    private Occupancy occupancies;

    @Column(name = "minimum_price")
    private float minimumPrice;

    @Column(name = "hotel_ratings")
    private float hotelRatings;
}
