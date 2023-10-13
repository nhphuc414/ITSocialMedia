package com.nhp.itsocialserver.dtos.requests;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
@Data
public class PostRequest implements Serializable {
    private String userId;
    private String communityId;
    private String content;
    private MultipartFile imageFile;
    private String status;
}
