package com.nhp.itsocialserver.dtos.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.io.Serializable;
import java.util.Date;

@Getter
public class JwtResponse implements Serializable {
    private final String token;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private final Date expirationDate;

    public JwtResponse(String jwtToken, Date expirationDate) {
        this.token = jwtToken;
        this.expirationDate = expirationDate;
    }
}
