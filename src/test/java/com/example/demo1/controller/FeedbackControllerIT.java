package com.example.demo1.controller;

import com.example.demo1.Demo1Application;
import com.example.demo1.dto.FeedbackDto;
import com.fasterxml.jackson.core.io.JsonEOFException;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

@SpringBootTest(classes = Demo1Application.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FeedbackControllerIT {
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    HttpHeaders headers = new HttpHeaders();

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    @Test
    public void findReviewForHotel() throws JSONException {
        HttpEntity<String>entity=new HttpEntity<String>(null,headers);
        ResponseEntity<String>response=restTemplate.exchange(
                createURLWithPort("/showReviewForHotel/1"),
                HttpMethod.GET,entity,String.class);
        //System.out.println(response);
        //<200,[{"hotel_id":1,"user_id":2,"rating":4.0,"review":"Located just near the fort in city(perfect location)."},{"hotel_id":1,"user_id":1,"rating":3.5,"review":"Hotel is good but rooms are very compact."}],[Vary:"Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers", Content-Type:"application/json", Transfer-Encoding:"chunked", Date:"Fri, 26 Feb 2021 11:45:39 GMT", Keep-Alive:"timeout=60", Connection:"keep-alive"]>
        //System.out.println(response.getBody());
        //[{"hotel_id":1,"user_id":2,"rating":4.0,"review":"Located just near the fort in city(perfect location)."},{"hotel_id":1,"user_id":1,"rating":3.5,"review":"Hotel is good but rooms are very compact."}]
        String expected="[{\"hotel_id\":1,\"user_id\":2,\"rating\":4.0,\"review\":\"Located just near the fort in city(perfect location).\"},{\"hotel_id\":1,\"user_id\":1,\"rating\":3.5,\"review\":\"Hotel is good but rooms are very compact.\"}]";
        JSONAssert.assertEquals(expected,response.getBody(),false);

    }

    @Test
    public void addReviewForHotel() throws JSONException{
        FeedbackDto feedbackDto=new FeedbackDto(2L,1L,(float)4.5,"Services are good");
        HttpEntity<FeedbackDto>entity=new HttpEntity<FeedbackDto>(feedbackDto,headers);
        ResponseEntity<String>response=restTemplate.exchange(
                createURLWithPort("/addReview/2/1"),
                HttpMethod.POST,entity,String.class);
        //System.out.println(response);
        //<200,{"hotel_id":2,"user_id":1,"rating":4.5,"review":"Services are good"},[Vary:"Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers", Content-Type:"application/json", Transfer-Encoding:"chunked", Date:"Fri, 26 Feb 2021 11:57:37 GMT", Keep-Alive:"timeout=60", Connection:"keep-alive"]>
        //System.out.println(response.getBody());
        //{"hotel_id":2,"user_id":1,"rating":4.5,"review":"Services are good"}
        String expected="{\"hotel_id\":2,\"user_id\":1,\"rating\":4.5,\"review\":\"Services are good\"}";
        JSONAssert.assertEquals(expected,response.getBody(),false);

    }
}

