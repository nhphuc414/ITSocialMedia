package com.nhp.itsocialserver.services;

import com.nhp.itsocialserver.dtos.requests.PostRequest;
import com.nhp.itsocialserver.pojos.*;

import java.util.List;

public interface PostService {
    List<Post> findAll();
    Post findById(int id);
    List<Comment> getComments(int id);
    Post create(PostRequest postRequest);
    Post edit(int id, PostRequest postRequest);
    void deleteById(int id);
}
