package com.nhp.itsocialserver.services;

import com.nhp.itsocialserver.dtos.requests.FollowRequest;
import com.nhp.itsocialserver.pojos.Follow;
import com.nhp.itsocialserver.pojos.User;

public interface FollowService {
    Follow findById(int id);
    Follow follow(FollowRequest followRequest);
    void unfollow(FollowRequest followRequest);
}
