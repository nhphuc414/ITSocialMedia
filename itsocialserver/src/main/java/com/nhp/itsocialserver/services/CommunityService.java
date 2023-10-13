package com.nhp.itsocialserver.services;

import com.nhp.itsocialserver.dtos.requests.CommunityRequest;
import com.nhp.itsocialserver.pojos.Comment;
import com.nhp.itsocialserver.pojos.Community;
import com.nhp.itsocialserver.pojos.Post;
import com.nhp.itsocialserver.pojos.User;

import java.util.List;

public interface CommunityService {

    Community getById(int id);
    List<User> getMembers(int id);
    List<Post> getCommunityTimeLine(int id);
    Community create(CommunityRequest communityRequest);
    Community edit(CommunityRequest communityRequest);
    void deleteById(int id);
}
