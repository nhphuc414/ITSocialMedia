package com.nhp.itsocialserver.controllers;

import com.nhp.itsocialserver.dtos.requests.FollowRequest;
import com.nhp.itsocialserver.dtos.requests.ReactionRequest;
import com.nhp.itsocialserver.dtos.responses.FollowResponse;
import com.nhp.itsocialserver.dtos.responses.ModelResponse;
import com.nhp.itsocialserver.mappers.FollowMapper;
import com.nhp.itsocialserver.pojos.Comment;
import com.nhp.itsocialserver.pojos.Follow;
import com.nhp.itsocialserver.pojos.Reaction;
import com.nhp.itsocialserver.services.FollowService;
import com.nhp.itsocialserver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/follow", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE},
        consumes = {MediaType.MULTIPART_FORM_DATA_VALUE,MediaType.APPLICATION_JSON_VALUE})
public class FollowController {
    @Autowired
    private FollowService followService;
    @Autowired
    private UserService userService;
    @PostMapping("/add")
    public ResponseEntity<?> create(@ModelAttribute FollowRequest followRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            String username = ((UserDetails) principal).getUsername();
            if(userService.findByUsername(username).getId().toString().equals(followRequest.getUserId()))
            {
                followService.follow(followRequest);
                return new ResponseEntity<>("Followed", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
    @DeleteMapping ("/delete")
    public ResponseEntity<?> delete(@ModelAttribute FollowRequest followRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            String username = ((UserDetails) principal).getUsername();
            if(userService.findByUsername(username).getId().toString().equals(followRequest.getUserId()))
            {
                followService.unfollow(followRequest);
                return new ResponseEntity<>("Unfollowed",HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
