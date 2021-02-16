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
public class UserDto {

    @JsonProperty("user_name")
    private String user_name;

    @JsonProperty("password")
    private String password;

    public static UserDto get(User user){
        return UserDto.builder()
                .user_name(user.getUser_name())
                .password(user.getPassword())
                .build();
    }
}
