package com.nhp.itsocialserver.mappers;

import com.nhp.itsocialserver.dtos.requests.UserRegisterRequest;
import com.nhp.itsocialserver.dtos.responses.UserResponse;
import com.nhp.itsocialserver.pojos.User;
import com.nhp.itsocialserver.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    @Autowired
    private ModelMapper mapper;

    public User toModel(UserRegisterRequest userRegister) {
        return mapper.map(userRegister, User.class);
    }
    public UserResponse toResponse(User user) {
        if (user==null) return null;
        return mapper.map(user, UserResponse.class);
    }
}
