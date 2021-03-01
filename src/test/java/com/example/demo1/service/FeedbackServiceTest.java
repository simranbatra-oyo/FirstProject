package com.example.demo1.service;

import com.example.demo1.dto.FeedbackDto;
import com.example.demo1.model.Feedback;
import com.example.demo1.model.Hotel;
import com.example.demo1.model.Occupancy;
import com.example.demo1.model.User;
import com.example.demo1.repository.FeedbackRepository;
import org.assertj.core.api.ObjectAssert;
import org.assertj.core.api.ThrowableTypeAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class FeedbackServiceTest {

    @InjectMocks
    private FeedbackService feedbackService;

    @Mock
    private FeedbackRepository repository;
    @Mock
    private HotelService hotelService;
    @Mock
    private UserService userService;

    @Test
    public void findAll() {
        User user1=new User(1L,"Rajeev Singh","abc","7623467423","rajeev@gmail.com");
        Hotel hotel1=new Hotel((long)1,"Hotel Indraprasth","8867896754","Near AIR Office, Shramik Nagar, Indore", Occupancy.SINGLE,(float) 700.0,(float) 3.0);
        Hotel hotel2=new Hotel((long)2,"Hotel Ashirwad","8902456123","Gandhi Nagar,Gujarat",Occupancy.DOUBLE,(float)1000,(float)4);

        when(repository.findAll()).thenReturn(Arrays.asList(
                new Feedback(1L,user1,hotel1,(float)4,"Located just near the fort in city(perfect location).")
                ,new Feedback(2L,user1,hotel2,(float)3.5,"So so")));

        List<FeedbackDto>feedbackDtoList=feedbackService.findAll();

        assertEquals(FeedbackDto.get(new Feedback(1L,user1,hotel1,(float)4,"Located just near the fort in city(perfect location).")),feedbackDtoList.get(0));
        assertEquals(FeedbackDto.get(new Feedback(2L,user1,hotel2,(float)3.5,"So so")),feedbackDtoList.get(1));
        assertEquals(2,feedbackDtoList.size());

        verify(repository,times(1)).findAll();
        verifyNoMoreInteractions(repository);
    }

    @Test
    void getFeedbackForHotel() {
        User user1=new User(1L,"Rajeev Singh","abc","7623467423","rajeev@gmail.com");
        User user2=new User(2L,"Geetika Joshi","abcd","9845673452","geetika@gmail.com");
        Hotel hotel1=new Hotel((long)1,"Hotel Indraprasth","8867896754","Near AIR Office, Shramik Nagar, Indore", Occupancy.SINGLE,(float) 700.0,(float) 3.0);

        Feedback feedback1=new Feedback(1L,user1,hotel1,(float)4,"Located just near the fort in city(perfect location).");
        Feedback feedback2=new Feedback(2L,user2,hotel1,(float)3.5,"Waste of money");

        when(repository.findFeedbacksByHotelId(1L)).thenReturn(Arrays.asList(feedback1,feedback2));

        List<FeedbackDto>feedbackDtoList=feedbackService.getFeedbackForHotel(1L);

        assertEquals(FeedbackDto.get(feedback1),feedbackDtoList.get(0));
        assertEquals(FeedbackDto.get(feedback2),feedbackDtoList.get(1));
        assertEquals(2,feedbackDtoList.size());

        verify(repository,times(1)).findFeedbacksByHotelId(any(Long.class));
        verifyNoMoreInteractions(repository);
    }

    @Test
    void addFeedback() throws Exception{
        FeedbackDto getFeedbackDtoFromParameter=new FeedbackDto(1L,1L,(float)4,"Located just near the fort in city(perfect location).");

        Hotel hotel1=new Hotel((long)1,"Hotel Indraprasth","8867896754","Near AIR Office, Shramik Nagar, Indore", Occupancy.SINGLE,(float) 700.0,(float) 3.0);
        when(hotelService.getHotelById(getFeedbackDtoFromParameter.getHotel_id())).thenReturn(hotel1);

        User user1=new User(1L,"Rajeev Singh","abc","7623467423","rajeev@gmail.com");
        when(userService.getUserGivenUserId(getFeedbackDtoFromParameter.getUser_id())).thenReturn(user1);

        assertNotNull(hotel1);
        assertNotNull(user1);
        assertNotNull(getFeedbackDtoFromParameter.getReview());

        when(repository.save(Feedback.get(getFeedbackDtoFromParameter,hotel1,user1))).thenReturn(Feedback.get(getFeedbackDtoFromParameter,hotel1,user1));
        FeedbackDto feedbackDto=feedbackService.addFeedback(new FeedbackDto(1L,1L,(float)4,"Located just near the fort in city(perfect location)."));

        assertEquals(feedbackDto,getFeedbackDtoFromParameter);
        assertEquals(getFeedbackDtoFromParameter,FeedbackDto.get(Feedback.get(getFeedbackDtoFromParameter,hotel1,user1)));

        verify(hotelService,times(1)).getHotelById(any(Long.class));
        verifyNoMoreInteractions(hotelService);
        verify(userService,times(1)).getUserGivenUserId(any(Long.class));
        verifyNoMoreInteractions(userService);
        verify(repository,times(1)).save(any(Feedback.class));
        verifyNoMoreInteractions(repository);
    }




    @Test
    void addFeedback_whenBadRequest() throws Exception{

        FeedbackDto getFeedbackDtoFromParameter=new FeedbackDto(1L,1L,(float)4,null);

        Hotel hotel1=new Hotel((long)1,"Hotel Indraprasth","8867896754","Near AIR Office, Shramik Nagar, Indore", Occupancy.SINGLE,(float) 700.0,(float) 3.0);
//        when(hotelService.getHotelById(getFeedbackDtoFromParameter.getHotel_id())).thenReturn(hotel1);

        User user1=new User(1L,"Rajeev Singh","abc","7623467423","rajeev@gmail.com");
//        when(userService.getUserGivenUserId(getFeedbackDtoFromParameter.getUser_id())).thenReturn(user1);

        assertNotNull(hotel1);
        assertNotNull(user1);
        assertNull(getFeedbackDtoFromParameter.getReview());
         //assertThat(assertThatExceptionOfType(HttpClientErrorException.BadRequest);
        Throwable exception = assertThrows(Exception.class,()->feedbackService.addFeedback(getFeedbackDtoFromParameter));
        //System.out.println(exception.getMessage());
        assertEquals("Bad Request", exception.getMessage());


//        when(repository.save(Feedback.get(getFeedbackDtoFromParameter,hotel1,user1))).thenReturn(Feedback.get(getFeedbackDtoFromParameter,hotel1,user1));
//        FeedbackDto feedbackDto=feedbackService.addFeedback(new FeedbackDto(1L,1L,(float)4,"Located just near the fort in city(perfect location)."));

//        assertEquals(feedbackDto,getFeedbackDtoFromParameter);
//        assertEquals(getFeedbackDtoFromParameter,FeedbackDto.get(Feedback.get(getFeedbackDtoFromParameter,hotel1,user1)));

        //verify(hotelService,times(1)).getHotelById(any(Long.class));
        //verifyNoMoreInteractions(hotelService);
        //verify(userService,times(1)).getUserGivenUserId(any(Long.class));
        //verifyNoMoreInteractions(userService);
        //verify(repository,times(0)).save(any(Feedback.class));
        //verifyNoMoreInteractions(repository);
    }



}