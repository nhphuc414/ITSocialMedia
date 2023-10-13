package com.nhp.itsocialserver.services;

import com.nhp.itsocialserver.pojos.User;

public interface FollowService {
    void follow(int id);
    void unfollow(int id);
}
