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

    public Post toModel(PostRequest postRequest) {
        return mapper.map(postRequest, Post.class);
    }
    public PostResponse toResponse(Post post){
            if (post==null) return null;
            PostResponse res = mapper.map(post, PostResponse.class);
            res.setCommunity(communityMapper.toResponse(post.getCommunityId()));
            res.setCountComments(post.getCommentSet()==null?0:post.getCommentSet().size());
            res.setCountReactions(post.getReactionSet()==null?0:post.getReactionSet().size());
            return res;
    }
}
