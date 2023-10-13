package com.nhp.itsocialserver.services.impl;

import com.nhp.itsocialserver.dtos.requests.CommentRequest;
import com.nhp.itsocialserver.mappers.CommentMapper;
import com.nhp.itsocialserver.pojos.Comment;
import com.nhp.itsocialserver.pojos.Post;
import com.nhp.itsocialserver.pojos.Reaction;
import com.nhp.itsocialserver.repositories.CommentRepository;
import com.nhp.itsocialserver.repositories.PostRepository;
import com.nhp.itsocialserver.services.CloudinaryService;
import com.nhp.itsocialserver.services.CommentService;
import com.nhp.itsocialserver.services.PostService;
import com.nhp.itsocialserver.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
@Service
@Transactional
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private CloudinaryService cloudinaryService;
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;
    @Override
    public Comment findById(int id) {
        return commentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Comment> getSubComment(int id) {
        List<Comment> comments = new ArrayList<>(this.findById(id).getCommentList());
        Collections.reverse(comments);
        return comments;
    }

    @Override
    public List<Reaction> getReactions(int id) {
        return new ArrayList<>(this.findById(id).getReactionList());
    }

    @Override
    public Comment create(CommentRequest commentRequest) {
        Comment comment = commentMapper.toModel(commentRequest);
        if (commentRequest.getPostId()!=null)comment.setPostId(postService.findById(Integer.parseInt(commentRequest.getPostId())));
        if (commentRequest.getUserId()!=null)comment.setUserId(userService.findById(Integer.parseInt(commentRequest.getUserId())));
        if (commentRequest.getCommentParentId()!=null) comment.setCommentParentId(this.findById(Integer.parseInt(commentRequest.getCommentParentId())));
        if (commentRequest.getImageFile() != null && !commentRequest.getImageFile().isEmpty() && commentRequest.getImageFile().getSize() > 0){
            comment.setImage(cloudinaryService.uploadImage(commentRequest.getImageFile()));
        }
        comment.setStatus("PUBLIC");
        comment.setCreatedDate(new Date());
        return this.commentRepository.saveAndFlush(comment);
    }

    @Override
    public Comment edit(int id, CommentRequest commentRequest) {
        Comment comment = this.findById(id);
        if(commentRequest.getContent()!=null)comment.setContent(commentRequest.getContent());
        if(commentRequest.getStatus()!=null)comment.setStatus(commentRequest.getStatus());
        if (commentRequest.getImageFile() != null && !commentRequest.getImageFile().isEmpty() && commentRequest.getImageFile().getSize() > 0){
            comment.setImage(cloudinaryService.uploadImage(commentRequest.getImageFile()));
        }
        comment.setUpdatedDate(new Date());
        return this.commentRepository.saveAndFlush(comment);
    }

    @Override
    public void deleteById(int id) {
        commentRepository.deleteById(id);
    }
}
