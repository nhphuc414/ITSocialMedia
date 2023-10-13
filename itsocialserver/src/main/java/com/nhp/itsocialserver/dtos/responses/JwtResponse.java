package com.nhp.itsocialserver.dtos.responses;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class JwtResponse implements Serializable {
    private final String token;
    private final String expirationDate;

    public JwtResponse(String jwtToken, String expirationDate) {
        this.token = jwtToken;
        this.expirationDate = expirationDate;
    }
}
