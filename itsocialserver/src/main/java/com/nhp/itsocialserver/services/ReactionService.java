package com.nhp.itsocialserver.services;

import com.nhp.itsocialserver.dtos.requests.ReactionRequest;
import com.nhp.itsocialserver.pojos.Comment;
import com.nhp.itsocialserver.pojos.Post;
import com.nhp.itsocialserver.pojos.Reaction;
import com.nhp.itsocialserver.pojos.Tag;

import java.util.List;

public interface ReactionService {
    Reaction findById(int id);

    boolean isReaction(int userId,int postId);
    Reaction create(ReactionRequest reactionRequest);
    Reaction edit(int id, ReactionRequest reactionRequest);
    void deleteById(int id);
}
