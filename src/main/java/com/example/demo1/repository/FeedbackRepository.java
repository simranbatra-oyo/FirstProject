package com.example.demo1.repository;

import com.example.demo1.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

//    @Query("SELECT f FROM Feedback f WHERE f.hotel = ?1")
//    List<Feedback> findRoomByStatus(Long hotelId);

    @Query(value = "SELECT * FROM feedbacks f WHERE f.hotel_id = ?1", nativeQuery = true)
    List<Feedback> findFeedbacksByHotelId(Long hotel_id);

//    public Feedback save(Feedback feedback){
//        feedback.add(feedback);
//    }

//    public Todo save(Todo todo) {
//        if(todo.getId()==-1 || todo.getId()==0) {
//            todo.setId(++idCounter);
//            todos.add(todo);
//        } else {
//            deleteById(todo.getId());
//            todos.add(todo);
//        }
//        return todo;
//    }

}
