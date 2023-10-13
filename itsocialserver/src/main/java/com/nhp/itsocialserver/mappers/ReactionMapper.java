package com.nhp.itsocialserver.mappers;

import com.nhp.itsocialserver.dtos.requests.ReactionRequest;
import com.nhp.itsocialserver.dtos.responses.ReactionResponse;
import com.nhp.itsocialserver.dtos.responses.UserResponse;
import com.nhp.itsocialserver.pojos.Reaction;
import com.nhp.itsocialserver.pojos.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReactionMapper {
    @Autowired
    private ModelMapper mapper;
    @Autowired private UserMapper userMapper;
    @Autowired
    private PostMapper postMapper;
    @Autowired private CommentMapper commentMapper;
    public Reaction toModel(ReactionRequest reactionRequest){
        return mapper.map(reactionRequest,Reaction.class);
    }
    public ReactionResponse toResponse(Reaction reaction) {
        if (reaction==null) return null;
        ReactionResponse res = mapper.map(reaction,ReactionResponse.class);
        res.setUser(userMapper.toResponse(reaction.getUserId()));
        res.setPost(postMapper.toResponse(reaction.getPostId()));
        res.setComment(commentMapper.toResponse(reaction.getCommentId()));
        return res;
    }
}
