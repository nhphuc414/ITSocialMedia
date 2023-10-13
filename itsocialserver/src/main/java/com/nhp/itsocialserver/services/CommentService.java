package com.nhp.itsocialserver.services;

import com.nhp.itsocialserver.dtos.requests.CommentRequest;
import com.nhp.itsocialserver.pojos.Comment;
import com.nhp.itsocialserver.pojos.Post;
import com.nhp.itsocialserver.pojos.Reaction;
import com.nhp.itsocialserver.pojos.User;

import java.util.List;
import java.util.Set;

public interface CommentService {
    Comment findById(int id);
    List<Comment> getSubComment(int id);
    List<Reaction> getReactions(int id);
    Comment create(CommentRequest commentRequest);
    Comment edit(int id, CommentRequest commentRequest);
    void deleteById(int id);
}
