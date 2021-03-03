package com.example.demo1.service;

import com.example.demo1.model.Hotel;
import com.example.demo1.model.Occupancy;
import com.example.demo1.repository.HotelRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.TestPropertySource;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@TestPropertySource(locations = {"classpath:com/example/demo1/"})
//@Configuration
//@PropertySource({"classpath:com/example/demo1/"})
class HotelServiceTest {

    @InjectMocks
    private HotelService hotelService;

    @Mock
    private HotelRepository repository;

    @Test
    void findAll() {

        when(repository.findAll()).thenReturn(Arrays.asList(
                new Hotel((long)1,"Hotel Indraprasth","8867896754","Near AIR Office, Shramik Nagar, Indore", Occupancy.SINGLE,(float) 700.0,(float) 3.0)
                ,new Hotel((long)2,"Hotel Ashirwad","8902456123","Gandhi Nagar,Gujarat",Occupancy.DOUBLE,(float)1000,(float)4)));

        List<Hotel> hotelList=hotelService.findAll();

        assertEquals(new Hotel((long)1,"Hotel Indraprasth","8867896754","Near AIR Office, Shramik Nagar, Indore", Occupancy.SINGLE,(float) 700.0,(float) 3.0),hotelList.get(0));
        assertEquals(new Hotel((long)2,"Hotel Ashirwad","8902456123","Gandhi Nagar,Gujarat",Occupancy.DOUBLE,(float)1000,(float)4),hotelList.get(1));
        assertEquals(2,hotelList.size());

        verify(repository,times(1)).findAll();
        verifyNoMoreInteractions(repository);

    }

    @Test
    void getHotelById() {

        Hotel returned=new Hotel((long)1,"Hotel Indraprasth","8867896754","Near AIR Office, Shramik Nagar, Indore", Occupancy.SINGLE,(float) 700.0,(float) 3.0);

        when(repository.findById(1L)).thenReturn(Optional.of(returned));

        Hotel hotel=hotelService.getHotelById(1l);

        assertEquals(new Hotel((long)1,"Hotel Indraprasth","8867896754","Near AIR Office, Shramik Nagar, Indore", Occupancy.SINGLE,(float) 700.0,(float) 3.0),hotel);

        verify(repository,times(1)).findById(1L);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void save() {

        Hotel added=new Hotel((long)1,"Hotel Indraprasth","8867896754","Near AIR Office, Shramik Nagar, Indore", Occupancy.SINGLE,(float) 700.0,(float) 3.0);

        when(repository.save(new Hotel((long)1,"Hotel Indraprasth","8867896754","Near AIR Office, Shramik Nagar, Indore", Occupancy.SINGLE,(float) 700.0,(float) 3.0))).thenReturn(added);

        Hotel newHotel=hotelService.save(added);

        assertThat(newHotel).isNotNull();

        verify(repository,times(1)).save(newHotel);
        verify(repository,times(1)).save(any(Hotel.class));
        verifyNoMoreInteractions(repository);

    }

    @Test
    void getHotelByPattern() {

        when(repository.searchByHotelNameLike("%"+"Hotel"+"%")).thenReturn(Arrays.asList(new Hotel((long)1,"Hotel Indraprasth","8867896754","Near AIR Office, Shramik Nagar, Indore", Occupancy.SINGLE,(float) 700.0,(float) 3.0)
                ,new Hotel((long)2,"Hotel Ashirwad","8902456123","Gandhi Nagar,Gujarat",Occupancy.DOUBLE,(float)1000,(float)4)));

        List<Hotel> hotelList=hotelService.getHotelByPattern("Hotel");

        assertEquals(new Hotel((long)1,"Hotel Indraprasth","8867896754","Near AIR Office, Shramik Nagar, Indore", Occupancy.SINGLE,(float) 700.0,(float) 3.0),hotelList.get(0));
        assertEquals(new Hotel((long)2,"Hotel Ashirwad","8902456123","Gandhi Nagar,Gujarat",Occupancy.DOUBLE,(float)1000,(float)4),hotelList.get(1));
        assertEquals(2,hotelList.size());

        verify(repository,times(1)).searchByHotelNameLike(any(String.class));
        verifyNoMoreInteractions(repository);
    }
}