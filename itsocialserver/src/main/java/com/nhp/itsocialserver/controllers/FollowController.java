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

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/follow")
public class FollowController {
    @Autowired
    private FollowService followService;
    @Autowired
    private UserService userService;
    @Autowired
    private  FollowMapper followMapper;

    @GetMapping("{followingId}/is-following")
    public ResponseEntity<Boolean> isFollowing(@PathVariable int followingId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            String username = ((UserDetails) principal).getUsername();
            int userId = userService.findByUsername(username).getId();
            return new ResponseEntity<>(followService.isFollowing(userId, followingId), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
    @GetMapping("/get-all-following")
    public ResponseEntity<?> getAllFollowing(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            String username = ((UserDetails) principal).getUsername();
            int userId = userService.findByUsername(username).getId();
            List<FollowResponse> following = followService.findallByUserId_Id(userId).stream().map(f->followMapper.toResponse(f)).toList();
            return ResponseEntity.ok(following);
        }
        return ResponseEntity.badRequest().body("UNAUTHORIZED");
    }
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
    @DeleteMapping("{followingId}/delete")
    public ResponseEntity<?> delete(@PathVariable String followingId) {
        System.out.println(followingId);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            String username = ((UserDetails) principal).getUsername();
            FollowRequest followRequest = new FollowRequest();
            followRequest.setUserId(userService.findByUsername(username).getId().toString());
            followRequest.setFollowingId(String.valueOf(followingId));
            followService.unfollow(followRequest);
            return new ResponseEntity<>("Unfollowed",HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
