package com.nhp.itsocialserver.mappers;

import com.nhp.itsocialserver.dtos.requests.FollowRequest;
import com.nhp.itsocialserver.dtos.responses.FollowResponse;
import com.nhp.itsocialserver.pojos.Follow;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FollowMapper {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private UserMapper userMapper;

    public Follow toModel(FollowRequest followRequest){
        return mapper.map(followRequest,Follow.class);
    }
    public FollowResponse toResponse(Follow follow){
        if (follow==null) return null;
        FollowResponse res = mapper.map(follow,FollowResponse.class);
        res.setUser(userMapper.toResponse(follow.getUserId()));
        res.setFollowing(userMapper.toResponse(follow.getFollowingId()));
        return res;
    }

}
