package com.nhp.itsocialserver.dtos.requests;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
public class UserChangeRequest implements Serializable {
    private String password;
    private String confirmPassword;
    private String fullName;
    private String email;
    private MultipartFile avatarFile;
    private MultipartFile bgImageFile;
}
