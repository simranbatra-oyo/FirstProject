package com.example.demo1.controller;

import com.example.demo1.dto.FeedbackDto;
import com.example.demo1.service.FeedbackService;
import com.example.demo1.service.HotelService;
import com.sun.tools.javac.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FeedbackController.class)
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@TestPropertySource(locations = {"classpath:com/example/demo1/"})
//@Configuration
//@PropertySource({"classpath:com/example/demo1/"})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FeedbackControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FeedbackService feedbackService;

    @Test
    void findReviewForHotel() throws Exception{
        FeedbackDto found=new FeedbackDto(1L,1L,(float)4,"Located just near the fort in city(perfect location).");
        when(feedbackService.getFeedbackForHotel(1L)).thenReturn(List.of(found));
        RequestBuilder request= MockMvcRequestBuilders.get("/showReviewForHotel/{hotel_id}",1L).accept(MediaType.APPLICATION_JSON);
        MvcResult result=mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"hotel_id\": 1,\"user_id\": 1,\"rating\": 4.0,\"review\": \"Located just near the fort in city(perfect location).\"}]"))
                .andReturn();
        verify(feedbackService,times(1)).getFeedbackForHotel(1L);
        verifyNoMoreInteractions(feedbackService);

    }

    @Test
    void addReviewForHotel() throws Exception{
        FeedbackDto found=new FeedbackDto(1L,1L,(float)4,"Located just near the fort in city(perfect location).");
        when(feedbackService.addFeedback(any(FeedbackDto.class))).thenReturn(found);
        RequestBuilder request=MockMvcRequestBuilders.post("/addReview/{hotel_id}/{user_id}",1L,1L)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"hotel_id\": 1,\"user_id\": 1,\"rating\": 4.0,\"review\": \"Located just near the fort in city(perfect location).\"}")
                .contentType(MediaType.APPLICATION_JSON);
//        verify(feedbackService,times(1)).addFeedback(any(FeedbackDto.class));
//        verifyNoMoreInteractions(feedbackService);

    }
}