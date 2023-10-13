package com.nhp.itsocialserver.dtos.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nhp.itsocialserver.pojos.User;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class PostResponse implements Serializable {
    private int id;
    private UserResponse user;
    private CommunityResponse community;
    private String content;
    private String image;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private Date createdDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private Date updatedDate;
    private String status;
    private int countReactions;
    private int countComments;
}
