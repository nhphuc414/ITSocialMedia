package com.nhp.itsocialserver.dtos.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
public class UserResponse implements Serializable {
    private int id;
    private String username;
    private String image;
    private String bgImage;
    private String email;
    private String fullName;
    private String role;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Date createdDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Date updatedDate;
}
