package com.example.demo1.repository;

import com.example.demo1.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends CrudRepository<Feedback, Long>, JpaRepository<Feedback, Long> {

    @Query(value = "SELECT * FROM feedbacks f WHERE f.hotel_id = ?1", nativeQuery = true)
    List<Feedback> findFeedbacksByHotelId(Long hotelId);
}
