package com.example.demo1.repository;

import com.example.demo1.model.Hotel;
import com.example.demo1.model.Occupancy;
import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.TestPropertySource;


import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;



@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@TestPropertySource(locations = {"classpath:com/example/demo1/"})
//@TestPropertySource(locations = {"classpath:com/example/demo1/"})
//@Configuration
//@PropertySource({"classpath:com/example/demo1/"})

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HotelRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private HotelRepository repository;


    @Test
    public void findAll(){

        List<Hotel> hotels=repository.findAll();
        assertEquals(4,hotels.size());
        //System.out.println(hotels);
//      JSONAssert.assertEquals("[{hotel_id=1},{hotel_id= 2},{hotel_id=3},{hotel_id=4}]", (JSONArray) hotels,false);
        assertEquals(1L,hotels.get(0).getHotel_id());
        assertEquals(2L,hotels.get(1).getHotel_id());
        assertEquals(3L,hotels.get(2).getHotel_id());
        assertEquals(4L,hotels.get(3).getHotel_id());
    }

    @Test
    public void findById_ifIdExist(){
        Hotel hotel=repository.findById(1L).orElse(null);
        Hotel expected=new Hotel((long)1,"Hotel Indraprasth","8867896754","Near AIR Office, Shramik Nagar, Indore", Occupancy.SINGLE,(float) 700.0,(float) 3.0);

        assertNotNull(hotel);
        assertEquals(expected,hotel);
    }
    @Test
    public void findById_ifIdDoesNotExist(){
        Hotel hotel=repository.findById(5L).orElse(null);

        assertNull(hotel);
    }

    @Transactional
    @Test
    public void save(){
        Hotel hotel=new Hotel(null,"Hotel Royal","7623145670","33 kent street,Bhopal", Occupancy.SINGLE,(float)1250.0,(float) 4.5);
        //entityManager.persist(hotel);
        Hotel addedHotel=repository.save(hotel);
        //System.out.println(addedHotel);
        assertThat(addedHotel).isNotNull();
        //assertThat(hotel.getHotel_id()).isEqualTo(5L);
    }

    @Test
    void searchByHotelNameLike() {
        List<Hotel>hotelList=repository.searchByHotelNameLike("hotel");
//        for(Hotel hotel:hotelList){
//            System.out.println(hotel);
//        }
//        Hotel(hotel_id=1, hotel_name=Hotel Indraprasth, hotel_contact_no=8867896754, hotel_address=Near AIR Office, Shramik Nagar, Indore, occupancies=SINGLE, minimum_price=700.0, hotel_ratings=3.0)
//        Hotel(hotel_id=2, hotel_name=Hotel Ashirwad, hotel_contact_no=8902456123, hotel_address=Gandhi Nagar,Gujarat, occupancies=DOUBLE, minimum_price=1000.0, hotel_ratings=4.5)
//        Hotel(hotel_id=4, hotel_name=Hotel Inn Express Hyderabad Banjara, hotel_contact_no=7131335610, hotel_address=Erranmannzil Colony,Somajiguda,Hyderabad, occupancies=SINGLE, minimum_price=1500.0, hotel_ratings=4.0)

        assertThat(hotelList).size().isEqualTo(3);
        assertThat(hotelList.get(0).getHotel_id()).isEqualTo(1L);
        assertThat(hotelList.get(1).getHotel_id()).isEqualTo(2L);
        assertThat(hotelList.get(2).getHotel_id()).isEqualTo(4L);
    }

}