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
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
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
        return postRepository.findAll(Sort.by(Sort.Direction.DESC, "createdDate")).stream().filter(p->!p.getStatus().equals("HIDDEN")).toList();
    }

    @Override
    public Post findById(int id) {
        return postRepository.findById(id).orElse(null);
    }

    @Override
    public List<Comment> getComments(int id) {
        List<Comment> comments = new ArrayList<>(this.findById(id).getCommentList());
        Collections.reverse(comments);
        return comments;
    }

    @Override
    public List<Reaction> getReactions(int id) {
        return new ArrayList<>(this.findById(id).getReactionList());
    }

    @Override
    public Post create(PostRequest postRequest) {
        Post post = postMapper.toModel(postRequest);
        if (postRequest.getUserId()!=null)post.setUserId(userService.findById(Integer.parseInt(postRequest.getUserId())));
        if (postRequest.getCommunityId()!=null) post.setCommunityId(communityService.getById(Integer.parseInt(postRequest.getCommunityId())));
        if (postRequest.getImageFile() != null && !postRequest.getImageFile().isEmpty() && postRequest.getImageFile().getSize() > 0){
            post.setImage(cloudinaryService.uploadImage(postRequest.getImageFile()));
        }
        post.setCreatedDate(new Date());
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
