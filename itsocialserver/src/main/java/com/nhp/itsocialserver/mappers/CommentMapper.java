package com.nhp.itsocialserver.mappers;

import com.nhp.itsocialserver.dtos.requests.CommentRequest;
import com.nhp.itsocialserver.dtos.responses.CommentResponse;
import com.nhp.itsocialserver.dtos.responses.PostResponse;
import com.nhp.itsocialserver.pojos.Comment;
import com.nhp.itsocialserver.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PostMapper postMapper;
    public Comment toModel(CommentRequest commentRequest){
        return mapper.map(commentRequest, Comment.class);
    }
    public CommentResponse toResponse(Comment comment){
        if (comment==null) return null;
        CommentResponse res = mapper.map(comment,CommentResponse.class);
        res.setCountReaction(comment.getReactionSet()==null?0:comment.getReactionSet().size());
        res.setCountReply(comment.getCommentSet()==null?0:comment.getCommentSet().size());
        res.setUser(userMapper.toResponse(comment.getUserId()));
        res.setPost(postMapper.toResponse(comment.getPostId()));
        res.setCommentParent(this.toResponse(comment.getCommentParentId()));
        return res;
    }
}
