package com.nhp.itsocialserver.dtos.requests;

import lombok.Data;

import java.io.Serializable;

@Data
public class ReactionRequest implements Serializable {
    private String postId;
    private String userId;
    private String commentId;
}
