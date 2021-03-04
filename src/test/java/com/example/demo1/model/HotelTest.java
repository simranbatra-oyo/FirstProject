package com.example.demo1.model;

import lombok.Generated;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

//@lombok.Generated
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HotelTest {

    public static final Long hotel_id=1L;
    public static final String hotel_name="Hotel Indraprasth";
    public static final String hotel_contact_no="8867896754";
    public static final String hotel_address="Near AIR Office, Shramik Nagar, Indore";
    public static final Occupancy occupancies=Occupancy.SINGLE;
    public static final float minimum_price=700.0f;
    public static final float hotel_ratings= 3.0f;

    private Hotel hotel;
    private Hotel hotel1;

    @BeforeEach
    void setUp() {
        hotel=new Hotel((long)1,"Hotel Indraprasth","8867896754","Near AIR Office, Shramik Nagar, Indore", Occupancy.SINGLE,(float) 700.0,(float) 3.0);
        hotel1=new Hotel();
    }

//    @AfterEach
//    void tearDown() {
//    }

    @Test
    void testHotelDetails() {
        assertEquals(hotel_id, hotel.getHotel_id());
        assertEquals(hotel_name,hotel.getHotel_name());
        assertEquals(hotel_contact_no,hotel.getHotel_contact_no());
        assertEquals(hotel_address,hotel.getHotel_address());
        assertEquals(occupancies,hotel.getOccupancies());
        assertEquals(minimum_price,hotel.getMinimum_price());
        assertEquals(hotel_ratings,hotel.getHotel_ratings());
    }
}