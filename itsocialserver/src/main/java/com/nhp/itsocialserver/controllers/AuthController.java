package com.nhp.itsocialserver.controllers;

import com.nhp.itsocialserver.dtos.requests.LoginRequest;
import com.nhp.itsocialserver.dtos.requests.UserRegisterRequest;
import com.nhp.itsocialserver.dtos.responses.JwtResponse;
import com.nhp.itsocialserver.dtos.responses.ModelResponse;
import com.nhp.itsocialserver.jwt.JwtTokenUtils;
import com.nhp.itsocialserver.mappers.UserMapper;
import com.nhp.itsocialserver.pojos.User;
import com.nhp.itsocialserver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/auth", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_JSON_VALUE}
        ,consumes = {MediaType.MULTIPART_FORM_DATA_VALUE,MediaType.APPLICATION_JSON_VALUE})
public class AuthController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtils jwtTokenUtils;
    private final UserMapper userMapper;

    @Autowired
    public AuthController(UserService userService, AuthenticationManager authenticationManager, JwtTokenUtils jwtTokenUtils, UserMapper userMapper) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtils = jwtTokenUtils;
        this.userMapper = userMapper;
    }
    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@ModelAttribute LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

            final UserDetails userDetails = userService.loadUserByUsername(request.getUsername());
            final String token = jwtTokenUtils.generateToken(userDetails);
            return ResponseEntity.ok(token);
        }
        catch (Exception e){
            return ResponseEntity.status(400).body(new ModelResponse(400, "", "Wrong Username or password!!"));
        }
    }
    @PostMapping("/register")
    public ResponseEntity<?> create(@ModelAttribute UserRegisterRequest userRequest) {
        System.out.println(userRequest);
        if (userService.findByUsername(userRequest.getUsername())!=null){
            return ResponseEntity.badRequest().body("Username already exists");
        }
        if (userService.findByEmail(userRequest.getEmail())!=null){
            return ResponseEntity.badRequest().body("Email already exists");
        }
        if (!userRequest.getPassword().equals(userRequest.getConfirmPassword())){
            return ResponseEntity.badRequest().body("Confirm Password not match");
        }
        User user = userService.create(userRequest);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User creation failed");
        }
        return new ResponseEntity<>(userMapper.toResponse(user), HttpStatus.CREATED);
    }
}
