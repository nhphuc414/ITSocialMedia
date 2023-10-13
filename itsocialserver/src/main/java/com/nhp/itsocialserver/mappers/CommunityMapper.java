package com.nhp.itsocialserver.mappers;

import com.nhp.itsocialserver.dtos.requests.CommunityRequest;
import com.nhp.itsocialserver.dtos.responses.CommunityResponse;
import com.nhp.itsocialserver.pojos.Community;
import com.nhp.itsocialserver.services.CommunityService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommunityMapper {
    @Autowired
    private ModelMapper mapper;
    @Autowired private UserMapper userMapper;

    public Community toModel(CommunityRequest communityRequest){
        return mapper.map(communityRequest,Community.class);
    }
    public CommunityResponse toResponse(Community community){
        if (community==null) return null;
        CommunityResponse res = mapper.map(community,CommunityResponse.class);
        res.setCountUsers(community.getCommunityUserSet()==null?0:community.getCommunityUserSet().size());
        res.setCountPosts(community.getPostSet()==null?0:community.getPostSet().size());
        res.setAdministrators(community.getCommunityUserSet().stream().filter(
                c -> c.getRole().equals("ADMIN")
        ).map(c -> userMapper.toResponse(c.getUserId())).toList());
        return res;
    }
}
