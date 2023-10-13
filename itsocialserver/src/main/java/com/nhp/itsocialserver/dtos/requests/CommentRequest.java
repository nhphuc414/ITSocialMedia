package com.nhp.itsocialserver.dtos.requests;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
public class CommentRequest implements Serializable {
    private String content;
    private MultipartFile imageFile;
    private String userId;
    private String postId;
    private String commentParentId;
    private String status;

}
