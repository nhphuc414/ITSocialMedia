package com.nhp.itsocialserver.dtos.responses;

import com.cloudinary.http44.api.Response;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
public class CommunityResponse implements Serializable {
    private int id;
    private String name;
    private String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Date createdDate;
    private int countUsers;
    private int countPosts;
    private List<UserResponse> administrators;

}
