package com.nhp.itsocialserver.controllers;

import com.nhp.itsocialserver.dtos.requests.LoginRequest;
import com.nhp.itsocialserver.dtos.responses.JwtResponse;
import com.nhp.itsocialserver.dtos.responses.ModelResponse;
import com.nhp.itsocialserver.jwt.JwtTokenUtils;
import com.nhp.itsocialserver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/auth/", produces = {MediaType.APPLICATION_JSON_VALUE}
        ,consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
public class AuthController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtils jwtTokenUtils;
    @Autowired
    public AuthController(UserService userService, AuthenticationManager authenticationManager, JwtTokenUtils jwtTokenUtils) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtils = jwtTokenUtils;
    }
    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@ModelAttribute LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        final UserDetails userDetails = userService
                .loadUserByUsername(request.getUsername());
        if (userDetails != null) {
            final String token = jwtTokenUtils.generateToken(userDetails);
            final Date expirationDate = this.jwtTokenUtils.getExpirationDateFromToken(token);
            String ex = expirationDate.toString();
            return ResponseEntity.ok(new ModelResponse(200, "Auth Successful", new JwtResponse(token, ex)));
        }
        else
            return ResponseEntity.status(400).body(new ModelResponse(400, "Error", ""));
    }
}
