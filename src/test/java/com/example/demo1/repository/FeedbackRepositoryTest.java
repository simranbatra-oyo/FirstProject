package com.example.demo1.repository;

import com.example.demo1.model.Feedback;
import com.example.demo1.model.Hotel;
import com.example.demo1.model.Occupancy;
import com.example.demo1.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@Rollback(value = false)
class FeedbackRepositoryTest {

    @Autowired
    private FeedbackRepository repository;

    @Test
    void findFeedbacksByHotelId() {
        List<Feedback> feedbackList=repository.findFeedbacksByHotelId(1L);
        //System.out.println(feedbackList);
        //[Feedback(feedback_id=1, user=User(user_id=2, user_name=Geetika Joshi, password=abcd, user_phone_no=9845673452, user_email_id=geetika@gmail.com), hotel=Hotel(hotel_id=1, hotel_name=Hotel Indraprasth, hotel_contact_no=8867896754, hotel_address=Near AIR Office, Shramik Nagar, Indore, occupancies=SINGLE, minimum_price=700.0, hotel_ratings=3.0), rating=4.0, review=Located just near the fort in city(perfect location).), Feedback(feedback_id=2, user=User(user_id=1, user_name=Rajeev Singh, password=abc, user_phone_no=7623467423, user_email_id=rajeev@gmail.com), hotel=Hotel(hotel_id=1, hotel_name=Hotel Indraprasth, hotel_contact_no=8867896754, hotel_address=Near AIR Office, Shramik Nagar, Indore, occupancies=SINGLE, minimum_price=700.0, hotel_ratings=3.0), rating=3.5, review=Hotel is good but rooms are very compact.)]
        //System.out.println(feedbackList.size()); //2
        assertThat(feedbackList).size().isEqualTo(2);

        assertThat(feedbackList.get(0).getFeedback_id()).isEqualTo(1L);
        assertThat(feedbackList.get(0).getUser().getUser_id()).isEqualTo(2L);

        assertThat(feedbackList.get(1).getFeedback_id()).isEqualTo(2L);
        assertThat(feedbackList.get(1).getUser().getUser_id()).isEqualTo(1L);
    }

    @Test
    void findAll(){
        List<Feedback> feedbackList=repository.findAll();

        assertThat(feedbackList).size().isEqualTo(2);

        assertThat(feedbackList.get(0).getFeedback_id()).isEqualTo(1L);
        assertThat(feedbackList.get(0).getHotel().getHotel_id()).isEqualTo(1L);
        assertThat(feedbackList.get(0).getUser().getUser_id()).isEqualTo(2L);

        assertThat(feedbackList.get(1).getFeedback_id()).isEqualTo(2L);
        assertThat(feedbackList.get(1).getHotel().getHotel_id()).isEqualTo(1L);
        assertThat(feedbackList.get(1).getUser().getUser_id()).isEqualTo(1L);
    }

    @Test
    void save(){

        User user=new User(1L,"Rajeev Singh","abc","7623467423","rajeev@gmail.com");
        Hotel hotel=new Hotel((long)2,"Hotel Ashirwad","8902456123","Gandhi Nagar,Gujarat",Occupancy.DOUBLE,(float)1000,(float)4);

        Feedback feedbackToSave=new Feedback(3L,user,hotel,(float)2.5,"Rooms are not clean");
        Feedback feedback=repository.save(feedbackToSave);
        assertThat(feedback).isNotNull();
       //assertThat(feedback.getFeedback_id()).isEqualTo(3L);
        //System.out.println(feedback);
        //Feedback(feedback_id=1, user=User(user_id=1, user_name=Rajeev Singh, password=abc, user_phone_no=7623467423, user_email_id=rajeev@gmail.com), hotel=Hotel(hotel_id=2, hotel_name=Hotel Ashirwad, hotel_contact_no=8902456123, hotel_address=Gandhi Nagar,Gujarat, occupancies=DOUBLE, minimum_price=1000.0, hotel_ratings=4.5), rating=2.5, review=Rooms are not clean)
    }

}