package com.nhp.itsocialserver.dtos.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class FollowResponse {
    private int id;
    private UserResponse user;
    private UserResponse following;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Date createdDate;
}
