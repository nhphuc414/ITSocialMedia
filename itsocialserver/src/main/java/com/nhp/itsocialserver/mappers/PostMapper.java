package com.nhp.itsocialserver.mappers;

import com.nhp.itsocialserver.dtos.requests.PostRequest;
import com.nhp.itsocialserver.dtos.requests.UserRegisterRequest;
import com.nhp.itsocialserver.dtos.responses.PostResponse;
import com.nhp.itsocialserver.pojos.CommunityUser;
import com.nhp.itsocialserver.pojos.Post;
import com.nhp.itsocialserver.pojos.User;
import com.nhp.itsocialserver.services.PostService;
import com.nhp.itsocialserver.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private CommunityMapper communityMapper;
    @Autowired private UserMapper userMapper;

    public Post toModel(PostRequest postRequest) {
        return mapper.map(postRequest, Post.class);
    }
    public PostResponse toResponse(Post post){
            if (post==null) return null;
            PostResponse res = mapper.map(post, PostResponse.class);
            res.setUser(userMapper.toResponse(post.getUserId()));
            res.setCommunity(communityMapper.toResponse(post.getCommunityId()));
            res.setCountComments(post.getCommentList()==null?0:post.getCommentList().size());
            res.setCountReactions(post.getReactionList()==null?0:post.getReactionList().size());
            return res;
    }
}
