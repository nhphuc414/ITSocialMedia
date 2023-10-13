package com.nhp.itsocialserver.dtos.requests;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.time.LocalDateTime;
@Data
public class UserRegisterRequest implements Serializable {
    private String username;
    private String password;
    private String confirmPassword;
    private String fullName;
    private String email;
    private MultipartFile avatarFile;
    private MultipartFile bgImageFile;
    private String role;
    {
        this.role = "USER";
    }
}
