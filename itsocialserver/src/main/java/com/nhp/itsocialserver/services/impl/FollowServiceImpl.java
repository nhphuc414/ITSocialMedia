package com.nhp.itsocialserver.services.impl;

import com.nhp.itsocialserver.dtos.requests.FollowRequest;
import com.nhp.itsocialserver.dtos.responses.FollowResponse;
import com.nhp.itsocialserver.pojos.Follow;
import com.nhp.itsocialserver.repositories.FollowRepository;
import com.nhp.itsocialserver.services.FollowService;
import com.nhp.itsocialserver.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Transactional
public class FollowServiceImpl implements FollowService {
    @Autowired
    private FollowRepository followRepository;
    @Autowired
    private UserService userService;

    @Override
    public Follow findById(int id) {
        return followRepository.findById(id).orElse(null);
    }

    @Override
    public Follow follow(FollowRequest followRequest) {
        Follow follow = new Follow();
        follow.setUserId(userService.findById(Integer.parseInt(followRequest.getUserId())));
        follow.setFollowingId(userService.findById(Integer.parseInt(followRequest.getFollowingId())));
        follow.setCreatedDate(new Date());
        return this.followRepository.saveAndFlush(follow);
    }

    @Override
    public void unfollow(FollowRequest followRequest) {
        Follow follow = followRepository.findByUserId_IdAndFollowingId_Id(Integer.parseInt(followRequest.getUserId()), Integer.parseInt(followRequest.getFollowingId())).orElse(null);
        if(follow!=null)
            followRepository.delete(follow);
    }

    @Override
    public List<Follow> findallByUserId_Id(int id) {
        List<Follow> following = this.followRepository.findAllByUserId_Id(id);
        Collections.reverse(following);
        return following;
    }

    @Override
    public boolean isFollowing(int userId, int followingId) {
        Optional<Follow> follow = followRepository.findByUserId_IdAndFollowingId_Id(userId,followingId);
        return follow.isPresent();
    }
}
