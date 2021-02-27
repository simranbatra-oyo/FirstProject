package com.example.demo1.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
//import javax.validation.constraints.NotNull;
//import org.hibernate.validator.constraints.Length;

@Entity
@Data
@Table(name = "hotels")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Hotel {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "hotel_id")
    private Long hotel_id;

//    @NotNull
//    @Length(max = 100)
    @Column(name="hotel_name")
    private String hotel_name;

//    @NotNull
    @Column(name = "hotel_contact_no")
    private String hotel_contact_no;

//    @NotNull
//    @Length(max=100)
    @Column(name = "hotel_address")
    private String hotel_address;

//    @NotNull
    @Column(name = "occupancies")
    @Enumerated(EnumType.STRING)
    //@Occupancies(anyOf ={Occupancy.SINGLE,Occupancy.DOUBLE})
    private Occupancy occupancies;

//    @NotNull
    @Column(name = "minimum_price")
    private float minimum_price;

//    @NotNull
    @Column(name = "hotel_ratings")
    private float hotel_ratings;

    public Hotel(){

    }

    public Hotel(Long hotel_id, String hotel_name, String hotel_contact_no, String hotel_address, Occupancy occupancies, float minimum_price, float hotel_ratings) {
        this.hotel_id = hotel_id;
        this.hotel_name = hotel_name;
        this.hotel_contact_no = hotel_contact_no;
        this.hotel_address = hotel_address;
        this.occupancies = occupancies;
        this.minimum_price = minimum_price;
        this.hotel_ratings = hotel_ratings;
    }

//    public Hotel(Long hotel_id, String hotel_name, String hotel_contact_no, String hotel_address, float minimum_price, float hotel_ratings) {
//        this.hotel_id = hotel_id;
//        this.hotel_name = hotel_name;
//        this.hotel_contact_no = hotel_contact_no;
//        this.hotel_address = hotel_address;
//        this.minimum_price = minimum_price;
//        this.hotel_ratings = hotel_ratings;
//    }

}
