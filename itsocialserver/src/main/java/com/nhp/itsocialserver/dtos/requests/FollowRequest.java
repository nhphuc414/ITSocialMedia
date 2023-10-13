package com.nhp.itsocialserver.dtos.requests;

import lombok.Data;

import java.io.Serializable;
@Data
public class FollowRequest implements Serializable {
    private String userId;
    private String followingId;
}
