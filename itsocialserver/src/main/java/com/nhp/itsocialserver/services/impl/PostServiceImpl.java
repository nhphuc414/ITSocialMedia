package com.nhp.itsocialserver.services.impl;

import com.nhp.itsocialserver.dtos.requests.PostRequest;
import com.nhp.itsocialserver.mappers.PostMapper;
import com.nhp.itsocialserver.pojos.*;
import com.nhp.itsocialserver.repositories.PostRepository;
import com.nhp.itsocialserver.services.CloudinaryService;
import com.nhp.itsocialserver.services.CommunityService;
import com.nhp.itsocialserver.services.PostService;
import com.nhp.itsocialserver.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private CloudinaryService cloudinaryService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommunityService communityService;
    @Override
    public List<Post> findAll() {
        return postRepository.findAll().stream().filter(p->!p.getStatus().equals("HIDDEN")).toList();
    }

    @Override
    public Post findById(int id) {
        return postRepository.findById(id).orElse(null);
    }

    @Override
    public List<Comment> getComments(int id) {
        List<Comment> comments = new ArrayList<>(this.findById(id).getCommentSet());
        Collections.reverse(comments);
        return comments;
    }

    @Override
    public Post create(PostRequest postRequest) {
        Post post = postMapper.toModel(postRequest);
        post.setUserId(userService.findById(Integer.parseInt(postRequest.getUserId())));
        post.setCommunityId(communityService.getById(Integer.parseInt(postRequest.getCommunityId())));
        if (postRequest.getImageFile() != null && !postRequest.getImageFile().isEmpty() && postRequest.getImageFile().getSize() > 0){
            post.setImage(cloudinaryService.uploadImage(postRequest.getImageFile()));
        }
        post.setCreatedDate(new Date());
        System.out.println(post.getUserId());
        return this.postRepository.saveAndFlush(post);
    }

    @Override
    public Post edit(int id, PostRequest postRequest) {
        Post post = this.findById(id);
        post.setContent(postRequest.getContent());
        if (postRequest.getStatus()!=null) post.setStatus(postRequest.getStatus());
        if (postRequest.getImageFile() != null && !postRequest.getImageFile().isEmpty() && postRequest.getImageFile().getSize() > 0){
            post.setImage(cloudinaryService.uploadImage(postRequest.getImageFile()));
        }
        post.setUpdatedDate(new Date());
        return this.postRepository.saveAndFlush(post);
    }


    @Override
    public void deleteById(int id) {
        postRepository.deleteById(id);
    }
}
