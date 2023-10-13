package com.nhp.itsocialserver.services;

import com.nhp.itsocialserver.dtos.requests.ReactionRequest;
import com.nhp.itsocialserver.pojos.Comment;
import com.nhp.itsocialserver.pojos.Post;
import com.nhp.itsocialserver.pojos.Reaction;
import com.nhp.itsocialserver.pojos.Tag;

import java.util.List;

public interface ReactionService {
    Reaction findById(int id);
    List<Reaction> getByComment(int id);
    List<Reaction> getByPost(int id);
    Reaction create(ReactionRequest reactionRequest);
    Reaction edit(int id, ReactionRequest reactionRequest);
    void deleteById(int id);
}
