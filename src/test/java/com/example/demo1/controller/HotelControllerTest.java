
package com.example.demo1.controller;

import com.example.demo1.model.Hotel;
import com.example.demo1.model.Occupancy;
import com.example.demo1.service.HotelService;
import com.sun.tools.javac.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HotelController.class)
class HotelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HotelService hotelService;

    @Test
    void findHotels() throws Exception{

        when(hotelService.findAll()).thenReturn(List.of(
              new Hotel((long)1,"Hotel Indraprasth","8867896754","Near AIR Office, Shramik Nagar, Indore", Occupancy.SINGLE,(float) 700.0,(float) 3.0)
              ,new Hotel((long)2,"Hotel Ashirwad","8902456123","Gandhi Nagar,Gujarat",Occupancy.DOUBLE,(float)1000,(float)4)));

        RequestBuilder request= MockMvcRequestBuilders.get("/showHotels").accept(MediaType.APPLICATION_JSON);

        MvcResult result=mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"hotel_id\": 1,\"hotel_name\": \"Hotel Indraprasth\",\"hotel_contact_no\": \"8867896754\",\"hotel_address\": \"Near AIR Office, Shramik Nagar, Indore\",\"occupancies\": \"SINGLE\",\"minimum_price\": 700.0,\"hotel_ratings\": 3.0},{}]"))
                .andExpect(jsonPath("$", hasSize(2)))
                .andReturn();

        verify(hotelService,times(1)).findAll();
        verifyNoMoreInteractions(hotelService);

    }

    @Test
    void findHotelsDetails_HotelEntryFound_ShouldReturnFoundHotelEntry() throws Exception{
        Hotel found=new Hotel((long)1,"Hotel Indraprasth","8867896754","Near AIR Office, Shramik Nagar, Indore", Occupancy.SINGLE,(float) 700.0,(float) 3.0);

        when(hotelService.getHotelById(1L)).thenReturn(found);

        RequestBuilder request=MockMvcRequestBuilders.get("/showHotelDetails/{hotel_id}",1L).accept(MediaType.APPLICATION_JSON);

        MvcResult result=mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("{\"hotel_id\": 1,\"hotel_name\": \"Hotel Indraprasth\",\"hotel_contact_no\": \"8867896754\",\"hotel_address\": \"Near AIR Office, Shramik Nagar, Indore\",\"occupancies\": \"SINGLE\",\"minimum_price\": 700.0,\"hotel_ratings\": 3.0}"))
                .andReturn();

        verify(hotelService,times(1)).getHotelById(1L);
        verifyNoMoreInteractions(hotelService);

        /*
        Todo found = new TodoBuilder()
                .id(1L)
                .description("Lorem ipsum")
                .title("Foo")
                .build();

        when(todoServiceMock.findById(1L)).thenReturn(found);

        mockMvc.perform(get("/api/todo/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.description", is("Lorem ipsum")))
                .andExpect(jsonPath("$.title", is("Foo")));

        verify(todoServiceMock, times(1)).findById(1L);
        verifyNoMoreInteractions(todoServiceMock);
         */
    }

    @Test
    void addHotel() throws Exception{

        Hotel added=new Hotel((long)1,"Hotel Indraprasth","8867896754","Near AIR Office, Shramik Nagar, Indore", Occupancy.SINGLE,(float) 700.0,(float) 3.0);

        when(hotelService.save(any(Hotel.class))).thenReturn(added);

        RequestBuilder request= MockMvcRequestBuilders.post("/add/hotel")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"hotel_id\": 1,\"hotel_name\": \"Hotel Indraprasth\",\"hotel_contact_no\": \"8867896754\",\"hotel_address\": \"Near AIR Office, Shramik Nagar, Indore\",\"occupancies\": \"SINGLE\",\"minimum_price\": 700.0,\"hotel_ratings\": 3.0}")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result= mockMvc.perform(request).andExpect(status().isOk()).andReturn();


//        MockHttpServletResponse response=result.getResponse();
//
//        assertEquals(HttpStatus.CREATED.value(),response.getStatus());
       // assertEquals();

               // .andExpect(status().isCreated())
              //  .andExpect(content().json("{\"hotel_id\": 1,\"hotel_name\": \"Hotel Indraprasth\",\"hotel_contact_no\": \"8867896754\",\"hotel_address\": \"Near AIR Office, Shramik Nagar, Indore\",\"occupancies\": \"SINGLE\",\"minimum_price\": 700.0,\"hotel_ratings\": 3.0}"))
              //  .andReturn();
//                .andExpect(content().json(added.toString()))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType("application/json;charset=UTF-8"));
//
        verify(hotelService,times(1)).save(any(Hotel.class));
        verifyNoMoreInteractions(hotelService);

//        Course mockCourse = new Course("1", "Smallest Number", "1",Arrays.asList("1", "2", "3", "4"));
//
//        // studentService.addCourse to respond back with mockCourse
//        Mockito.when(studentService.addCourse(Mockito.anyString(),Mockito.any(Course.class))).thenReturn(mockCourse);
//
//        // Send course as body to /students/Student1/courses
//        RequestBuilder requestBuilder = MockMvcRequestBuilders
//                .post("/students/Student1/courses")
//                .accept(MediaType.APPLICATION_JSON)
//                .content(exampleCourseJson)
//                .contentType(MediaType.APPLICATION_JSON);
//
//        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//
//        MockHttpServletResponse response = result.getResponse();
//
//        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
//
//        assertEquals("http://localhost/students/Student1/courses/1",response.getHeader(HttpHeaders.LOCATION));

    }

    @Test
    void findHotelPattern_HotelEntryFound_ShouldReturnFoundHotelEntry() throws Exception{
        Hotel found=new Hotel((long)1,"Hotel Indraprasth","8867896754","Near AIR Office, Shramik Nagar, Indore", Occupancy.SINGLE,(float) 700.0,(float) 3.0);

        when(hotelService.getHotelByPattern("Hotel Indraprasth")).thenReturn(List.of(found));

        RequestBuilder request=MockMvcRequestBuilders.get("/findHotelsByPattern/{hotel_name}","Hotel Indraprasth").accept(MediaType.APPLICATION_JSON);

        MvcResult result=mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"hotel_id\": 1,\"hotel_name\": \"Hotel Indraprasth\",\"hotel_contact_no\": \"8867896754\",\"hotel_address\": \"Near AIR Office, Shramik Nagar, Indore\",\"occupancies\": \"SINGLE\",\"minimum_price\": 700.0,\"hotel_ratings\": 3.0}]"))
                .andReturn();

        verify(hotelService,times(1)).getHotelByPattern("Hotel Indraprasth");
        verifyNoMoreInteractions(hotelService);

    }
}
