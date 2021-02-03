package com.example.demo1.repository;

import com.example.demo1.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends CrudRepository<Hotel,Long> , JpaRepository<Hotel,Long> {

}
