package com.nhp.itsocialserver.services.impl;

import com.nhp.itsocialserver.dtos.requests.ReactionRequest;
import com.nhp.itsocialserver.mappers.ReactionMapper;
import com.nhp.itsocialserver.pojos.Post;
import com.nhp.itsocialserver.pojos.Reaction;
import com.nhp.itsocialserver.repositories.ReactionRepository;
import com.nhp.itsocialserver.services.CommentService;
import com.nhp.itsocialserver.services.PostService;
import com.nhp.itsocialserver.services.ReactionService;
import com.nhp.itsocialserver.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
@Transactional
public class ReactionServiceImpl implements ReactionService {
    @Autowired
    private ReactionRepository reactionRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private ReactionMapper reactionMapper;

    @Override
    public Reaction findById(int id) {
        return reactionRepository.findById(id).orElse(null);
    }

    @Override
    public Reaction create(ReactionRequest reactionRequest) {
        Reaction reaction = new Reaction();
        reaction.setCreatedDate(new Date());
        reaction.setType("LIKE");
        if (reactionRequest.getUserId()!=null) reaction.setUserId(userService.findById(Integer.parseInt(reactionRequest.getUserId())));
        if (reactionRequest.getPostId()!=null) reaction.setPostId(postService.findById(Integer.parseInt(reactionRequest.getPostId())));
        if (reactionRequest.getCommentId()!=null) reaction.setCommentId(commentService.findById(Integer.parseInt(reactionRequest.getCommentId())));
        return this.reactionRepository.saveAndFlush(reaction);
    }

    @Override
    public Reaction edit(int id, ReactionRequest reactionRequest) {
        return null;
    }

    @Override
    public void deleteById(int id) {
        this.reactionRepository.deleteById(id);
    }
}
