package com.example.demo1.controller;

import com.example.demo1.Demo1Application;
import com.example.demo1.dto.UserDto;
import com.example.demo1.repository.JpaConfig;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

//import org.springframework.mock.web.MockHttpServletRequest;
//import org.springframework.mock.web.MockHttpServletResponse;
//import org.springframework.web.servlet.HandlerAdapter;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.function.support.HandlerFunctionAdapter;
////import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {Demo1Application.class, JpaConfig.class},webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@TestPropertySource(locations = {"classpath:com/example/demo1/"})
//@Configuration
//@PropertySource({"classpath:com/example/demo1/"})

@ActiveProfiles("test")
public class UserControllerIT {
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    HttpHeaders headers = new HttpHeaders();

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    @Test
    public void loginUser() throws JSONException{
        UserDto userDtoToCheck=new UserDto("Rajeev Singh","abc");
        HttpEntity<UserDto> entity=new HttpEntity<UserDto>(userDtoToCheck,headers);
        ResponseEntity<String>response=restTemplate.exchange(
                createURLWithPort("/login"),
                HttpMethod.POST,entity,String.class);
        //System.out.println(response);
        //<200,{"user_name":"Rajeev Singh","password":"abc"},[Vary:"Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers", Content-Type:"application/json", Transfer-Encoding:"chunked", Date:"Fri, 26 Feb 2021 11:01:04 GMT", Keep-Alive:"timeout=60", Connection:"keep-alive"]>
        //System.out.println(response.getBody());
        //{"user_name":"Rajeev Singh","password":"abc"}
        JSONAssert.assertEquals("{\"user_name\":\"Rajeev Singh\",\"password\":\"abc\"}",response.getBody(),false);

    }

    @Test
    public void getUser() throws Exception{
        //headers.setPragma("Rajeev Singh");
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        //System.out.println(headers.getPragma());
        //Rajeev Singh
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/getUser?user_name=Rajeev Singh"),
                HttpMethod.GET,entity,String.class);
        //System.out.println(response);
        //<400,{"timestamp":"2021-02-26T08:44:58.137+00:00","status":400,"error":"Bad Request","message":"","path":"/getUser"},[Vary:"Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers", Content-Type:"application/json", Transfer-Encoding:"chunked", Date:"Fri, 26 Feb 2021 08:44:58 GMT", Connection:"close"]>
        //<200,{"user_id":1,"user_name":"Rajeev Singh","password":"abc","user_phone_no":"7623467423","user_email_id":"rajeev@gmail.com"},[Vary:"Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers", Content-Type:"application/json", Transfer-Encoding:"chunked", Date:"Fri, 26 Feb 2021 09:59:08 GMT", Keep-Alive:"timeout=60", Connection:"keep-alive"]>
        String expected="{\"user_id\": 1,\"user_name\": \"Rajeev Singh\",\"password\": \"abc\",\"user_phone_no\": \"7623467423\",\"user_email_id\": \"rajeev@gmail.com\"}";
        JSONAssert.assertEquals(expected,response.getBody(),false);

        /*MockHttpServletRequest request=new MockHttpServletRequest();
        request.setRequestURI("/getUser");
        request.setMethod("GET");
        request.setParameter("user_name", "Rajeev Singh");
        MockHttpServletResponse response=new MockHttpServletResponse();
        HandlerAdapter handlerAdapter = new HandlerFunctionAdapter();
        ModelAndView mav = handlerAdapter.handle(request, response, UserController.class);
        assertEquals(200, response.getStatus());
        String expected="{\"user_id\": 1,\"user_name\": \"Rajeev Singh\",\"password\": \"abc\",\"user_phone_no\": \"7623467423\",\"user_email_id\": \"rajeev@gmail.com\"}";
        System.out.println(response);

         */

       /* RestAssured.baseURI ="https://samples.openweathermap.org/data/2.5/";
        RequestSpecification request = RestAssured.given();

        Response response = request.queryParam("q", "London,UK")
                .queryParam("appid", "2b1fd2d7f77ccf1b7de9b441571b39b8")
                .get("/weather");

        String jsonString = response.asString();
        System.out.println(response.getStatusCode());
        Assert.assertEquals(jsonString.contains("London"), true);

        */

        //JSONAssert.assertEquals(expected,response.getContentAsString(),false);

                //AnnotationMethodHandlerAdapter adapter=new AnnotationMethodHandlerAdapter();
    }

    @Test
    public void getUserId() throws JSONException {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/get/"+"Rajeev Singh"),
                HttpMethod.GET,entity,String.class);
        //System.out.println(response);
        //<200,1,[Vary:"Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers", Content-Type:"application/json", Transfer-Encoding:"chunked", Date:"Fri, 26 Feb 2021 08:26:18 GMT", Keep-Alive:"timeout=60", Connection:"keep-alive"]>
        //System.out.println(response.getBody());
        //1
        String expected="1";
        JSONAssert.assertEquals(expected,response.getBody(),false);
        /*
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/students/Student1/courses/Course1"),
				HttpMethod.GET, entity, String.class);

		String expected = "{id:Course1,name:Spring,description:10Steps}";

		JSONAssert.assertEquals(expected, response.getBody(), false);
         */
    }

}
