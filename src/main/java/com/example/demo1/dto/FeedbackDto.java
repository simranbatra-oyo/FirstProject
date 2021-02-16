package com.example.demo1.dto;
import com.example.demo1.model.Feedback;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class FeedbackDto{

    @JsonProperty("hotel_id")
    private Long hotel_id;

    @JsonProperty("user_id")
    private Long user_id;

    @JsonProperty("rating")
    private float rating;

    @JsonProperty("review")
    private String review;

    public static FeedbackDto get(Feedback feedback){
        return FeedbackDto.builder()
                .hotel_id(feedback.getHotel().getHotel_id())
                .user_id(feedback.getUser().getUser_id())
                .rating(feedback.getRating())
                .review(feedback.getReview())
                .build();
    }




}
