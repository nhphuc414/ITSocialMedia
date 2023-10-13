package com.nhp.itsocialserver.dtos.requests;

import lombok.Data;

import java.io.Serializable;
@Data
public class CommunityRequest implements Serializable {
    private String userId;
    private String name;
    private String description;
}
