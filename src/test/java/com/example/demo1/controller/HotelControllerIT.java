package com.example.demo1.controller;

import com.example.demo1.Demo1Application;
import com.example.demo1.model.Hotel;
import com.example.demo1.model.Occupancy;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;


import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@SpringBootTest(classes = Demo1Application.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class HotelControllerIT {
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    HttpHeaders headers = new HttpHeaders();

    private String createURLWithPort(String uri) {
            return "http://localhost:" + port + uri;
    }

    @Test
    public void findHotels() throws JSONException{
        String response=this.restTemplate.getForObject("/showHotels", String.class);
        //System.out.println(response);
        //[{"hotel_id":1,"hotel_name":"Hotel Indraprasth","hotel_contact_no":"8867896754","hotel_address":"Near AIR Office, Shramik Nagar, Indore","occupancies":"SINGLE","minimum_price":700.0,"hotel_ratings":3.0},
        // {"hotel_id":2,"hotel_name":"Hotel Ashirwad","hotel_contact_no":"8902456123","hotel_address":"Gandhi Nagar,Gujarat","occupancies":"DOUBLE","minimum_price":1000.0,"hotel_ratings":4.5},
        // {"hotel_id":3,"hotel_name":"Park Inn by Radisson","hotel_contact_no":"9721762312","hotel_address":"Racecourse Rd,near Railway Station,Gwalior","occupancies":"DOUBLE","minimum_price":1200.0,"hotel_ratings":4.0},
        // {"hotel_id":4,"hotel_name":"Hotel Inn Express Hyderabad Banjara","hotel_contact_no":"7131335610","hotel_address":"Erranmannzil Colony,Somajiguda,Hyderabad","occupancies":"SINGLE","minimum_price":1500.0,"hotel_ratings":4.0}]
        JSONAssert.assertEquals("[{\"hotel_id\": 1},{\"hotel_id\":2},{\"hotel_id\":3},{\"hotel_id\":4}]",response,false);

    }
    @Test
    public void findHotelsDetails() throws JSONException{
        String response=this.restTemplate.getForObject("/showHotelDetails/1", String.class);
        //System.out.println(response);
        //{"hotel_id":1,"hotel_name":"Hotel Indraprasth","hotel_contact_no":"8867896754","hotel_address":"Near AIR Office, Shramik Nagar, Indore","occupancies":"SINGLE","minimum_price":700.0,"hotel_ratings":3.0}
        JSONAssert.assertEquals("{\"hotel_name\": \"Hotel Indraprasth\"}",response,false);

    }
    @Test
    public void addHotel() throws JSONException{
        Hotel hotelToBeAdded=new Hotel((long)5,"Hotel Royal","7623145670","33 kent street,Bhopal", Occupancy.SINGLE,(float)1250.0,(float) 4.5);
        HttpEntity<Hotel> entity=new HttpEntity<>(hotelToBeAdded,headers);
        ResponseEntity<String> response=restTemplate.exchange(createURLWithPort("/add/hotel"), HttpMethod.POST,entity,String.class);
        //System.out.println(response);
        //<200,{"hotel_id":5,"hotel_name":"Hotel Royal","hotel_contact_no":"7623145670","hotel_address":"33 kent street,Bhopal","occupancies":"SINGLE","minimum_price":1250.0,"hotel_ratings":4.5},[Vary:"Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers", Content-Type:"application/json", Transfer-Encoding:"chunked", Date:"Fri, 26 Feb 2021 05:36:44 GMT", Keep-Alive:"timeout=60", Connection:"keep-alive"]>
        //System.out.println(response.getBody());
        //{"hotel_id":5,"hotel_name":"Hotel Royal","hotel_contact_no":"7623145670","hotel_address":"33 kent street,Bhopal","occupancies":"SINGLE","minimum_price":1250.0,"hotel_ratings":4.5}
        JSONAssert.assertEquals("{\"hotel_id\": 5}",response.getBody(),false);
        //System.out.println(response1.getStatusCode());
        //200 OK
        //JSONAssert.assertEquals(200,response1.getStatusCode().value());

        /*
        Course course = new Course("Course1", "Spring", "10Steps",
				Arrays.asList("Learn Maven", "Import Project", "First Example",
						"Second Example"));

		HttpEntity<Course> entity = new HttpEntity<Course>(course, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/students/Student1/courses"),
				HttpMethod.POST, entity, String.class);

		String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);

		assertTrue(actual.contains("/students/Student1/courses/"));
         */
    }
    @Test
    public void findHotelPattern() throws JSONException{
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        HttpEntity<String> response=restTemplate.exchange(
                createURLWithPort("/findHotelsByPattern/"+"Hotel"),
                HttpMethod.GET,entity,String.class
        );
        //System.out.println(response);
        //<200,[{"hotel_id":1,"hotel_name":"Hotel Indraprasth","hotel_contact_no":"8867896754","hotel_address":"Near AIR Office, Shramik Nagar, Indore","occupancies":"SINGLE","minimum_price":700.0,"hotel_ratings":3.0},{"hotel_id":2,"hotel_name":"Hotel Ashirwad","hotel_contact_no":"8902456123","hotel_address":"Gandhi Nagar,Gujarat","occupancies":"DOUBLE","minimum_price":1000.0,"hotel_ratings":4.5},{"hotel_id":4,"hotel_name":"Hotel Inn Express Hyderabad Banjara","hotel_contact_no":"7131335610","hotel_address":"Erranmannzil Colony,Somajiguda,Hyderabad","occupancies":"SINGLE","minimum_price":1500.0,"hotel_ratings":4.0}],[Vary:"Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers", Content-Type:"application/json", Transfer-Encoding:"chunked", Date:"Fri, 26 Feb 2021 06:28:34 GMT", Keep-Alive:"timeout=60", Connection:"keep-alive"]>
        //String expected= "[{\"hotel_id\":1,\"hotel_name\":\"Hotel Indraprasth\",\"hotel_contact_no\":\"8867896754\",\"hotel_address\":\"Near AIR Office, Shramik Nagar, Indore\",\"occupancies\":\"SINGLE\",\"minimum_price\":700.0,\"hotel_ratings\":3.0},{\"hotel_id\":2,\"hotel_name\":\"Hotel Ashirwad\",\"hotel_contact_no\":\"8902456123\",\"hotel_address\":\"Gandhi Nagar,Gujarat\",\"occupancies\":\"DOUBLE\",\"minimum_price\":1000.0,\"hotel_ratings\":4.5}, {\"hotel_id\":4,\"hotel_name\":\"Hotel Inn Express Hyderabad Banjara\",\"hotel_contact_no\":\"7131335610\",\"hotel_address\":\"Erranmannzil Colony,Somajiguda,Hyderabad\",\"occupancies\":\"SINGLE\",\"minimum_price\":1500.0,\"hotel_ratings\":4.0}]";
        String expected="[{\"hotel_id\":1,\"hotel_name\":\"Hotel Indraprasth\",\"hotel_contact_no\":\"8867896754\",\"hotel_address\":\"Near AIR Office, Shramik Nagar, Indore\",\"occupancies\":\"SINGLE\",\"minimum_price\":700.0,\"hotel_ratings\":3.0},{\"hotel_id\":2,\"hotel_name\":\"Hotel Ashirwad\",\"hotel_contact_no\":\"8902456123\",\"hotel_address\":\"Gandhi Nagar,Gujarat\",\"occupancies\":\"DOUBLE\",\"minimum_price\":1000.0,\"hotel_ratings\":4.5},{\"hotel_id\":4,\"hotel_name\":\"Hotel Inn Express Hyderabad Banjara\",\"hotel_contact_no\":\"7131335610\",\"hotel_address\":\"Erranmannzil Colony,Somajiguda,Hyderabad\",\"occupancies\":\"SINGLE\",\"minimum_price\":1500.0,\"hotel_ratings\":4.0}]";
        //System.out.println(response);
        //<200,[{"hotel_id":1,"hotel_name":"Hotel Indraprasth","hotel_contact_no":"8867896754","hotel_address":"Near AIR Office, Shramik Nagar, Indore","occupancies":"SINGLE","minimum_price":700.0,"hotel_ratings":3.0},{"hotel_id":2,"hotel_name":"Hotel Ashirwad","hotel_contact_no":"8902456123","hotel_address":"Gandhi Nagar,Gujarat","occupancies":"DOUBLE","minimum_price":1000.0,"hotel_ratings":4.5},{"hotel_id":4,"hotel_name":"Hotel Inn Express Hyderabad Banjara","hotel_contact_no":"7131335610","hotel_address":"Erranmannzil Colony,Somajiguda,Hyderabad","occupancies":"SINGLE","minimum_price":1500.0,"hotel_ratings":4.0}],[Vary:"Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers", Content-Type:"application/json", Transfer-Encoding:"chunked", Date:"Fri, 26 Feb 2021 07:59:27 GMT", Keep-Alive:"timeout=60", Connection:"keep-alive"]>
        //System.out.println(response.getBody());
        //[{"hotel_id":1,"hotel_name":"Hotel Indraprasth","hotel_contact_no":"8867896754","hotel_address":"Near AIR Office, Shramik Nagar, Indore","occupancies":"SINGLE","minimum_price":700.0,"hotel_ratings":3.0},{"hotel_id":2,"hotel_name":"Hotel Ashirwad","hotel_contact_no":"8902456123","hotel_address":"Gandhi Nagar,Gujarat","occupancies":"DOUBLE","minimum_price":1000.0,"hotel_ratings":4.5},{"hotel_id":4,"hotel_name":"Hotel Inn Express Hyderabad Banjara","hotel_contact_no":"7131335610","hotel_address":"Erranmannzil Colony,Somajiguda,Hyderabad","occupancies":"SINGLE","minimum_price":1500.0,"hotel_ratings":4.0}]

        JSONAssert.assertEquals(expected, response.getBody(), false);
         /*
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/students/Student1/courses/Course1"),
				HttpMethod.GET, entity, String.class);

		String expected = "{id:Course1,name:Spring,description:10Steps}";

		JSONAssert.assertEquals(expected, response.getBody(), false);
         */
    }

//    @Test
//    public void contextLoads() throws JSONException {
//    }
}
