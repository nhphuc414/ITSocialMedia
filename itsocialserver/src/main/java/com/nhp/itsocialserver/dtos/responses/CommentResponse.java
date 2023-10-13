package com.nhp.itsocialserver.dtos.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class CommentResponse implements Serializable {
    private int id;
    private String content;
    private String image;
    private int countReaction;
    private int countReply;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private Date createdDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private Date updateDate;
    private UserResponse user;
    private PostResponse post;
    private CommentResponse commentParent;
}
