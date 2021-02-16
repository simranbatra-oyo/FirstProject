package com.example.demo1.repository;

import com.example.demo1.model.Hotel;
import com.example.demo1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends CrudRepository<Hotel, Long>, JpaRepository<Hotel, Long> {

//    @Query(value = "SELECT h FROM hotels h WHERE h.hotel_name =?1 ", nativeQuery = true)
//    List<Hotel> findHotelByHotel_name(String hotel_name);

//    @Query(value = "SELECT count(*) FROM hotels h WHERE h.hotel_name LIKE '%?1%' ", nativeQuery = true)
//    Hotel findHotelByHotel_name(String hotel_name);

   // @WholeString LIKE '%' + @ExpressionToFind + '%'

//    @Query("select h from Hotel h order by substring(h.hotel_name)")
//    List<Hotel> findHotelByHotel_name(String hotel_name);

   // List<Movie> findByTitleContaining(String title);

  //  List<Hotel> findByHotel_nameIsLike(String hotel_name);
    //List<Hotel> findBy

//    @Query("SELECT h FROM Hotel h WHERE h.hotel_name LIKE %:hotel_name%")
//    List<Hotel> searchByHotelNameLike(@Param("hotel_name") String hotel_name);

    @Query("SELECT h from Hotel h where lower(h.hotel_name) like lower(concat('%', :hotel_name,'%'))")
    List<Hotel> searchByHotelNameLike(@Param("hotel_name") String hotel_name);

}
