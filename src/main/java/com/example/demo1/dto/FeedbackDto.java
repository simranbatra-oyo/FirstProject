package com.example.demo1.dto;

import com.example.demo1.model.Feedback;
import com.example.demo1.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class FeedbackDto {
    @JsonProperty("user_id")
    private long userId;

    @JsonProperty("hotel_id")
    private long hotelId;

    @JsonProperty("rating")
    private float rating;

    @JsonProperty("review")
    private String review;

    public static FeedbackDto get(Feedback feedback) {
        return FeedbackDto.builder()
                .hotelId(feedback.getHotel().getHotelId())
                .userId(feedback.getUser().getUserId())
                .rating(feedback.getRating())
                .review(feedback.getReview())
                .build();
    }
}
