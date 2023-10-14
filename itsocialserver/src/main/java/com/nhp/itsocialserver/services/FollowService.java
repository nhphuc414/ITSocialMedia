package com.nhp.itsocialserver.services;

import com.nhp.itsocialserver.dtos.requests.FollowRequest;
import com.nhp.itsocialserver.pojos.Follow;
import com.nhp.itsocialserver.pojos.User;

import java.util.List;

public interface FollowService {
    Follow findById(int id);
    Follow follow(FollowRequest followRequest);
    void unfollow(FollowRequest followRequest);
    List<Follow> findallByUserId_Id(int id);
    boolean isFollowing(int userId, int followingId);
}
