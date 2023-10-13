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
@RequestMapping(value = "/api/follow/", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE},
        consumes = {MediaType.MULTIPART_FORM_DATA_VALUE,MediaType.APPLICATION_JSON_VALUE})
public class FollowController {
    @Autowired
    private UserService userService;
    @Autowired
    private FollowService followService;
    @Autowired
    private FollowMapper followMapper;
    @GetMapping("/{id}/")
    public ResponseEntity<?> getInfo(@PathVariable int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Follow follow = followService.findById(id);
            if (follow ==null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Follow not found");
            ModelResponse response = new ModelResponse(200,"Follow Info",
                    followMapper.toResponse(follow));
            return ResponseEntity.ok(response);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
    @PostMapping("/follow/")
    public ResponseEntity<?> create(@ModelAttribute FollowRequest followRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                String username = ((UserDetails) principal).getUsername();
                followRequest.setUserId(userService.findByUsername(username).getId().toString());
                FollowResponse followResponse = followMapper.toResponse(followService.follow(followRequest));
                if (followResponse == null) {
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("Follow creation failed");
                }
                return ResponseEntity.status(HttpStatus.CREATED).body("Follow Successful");
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
    @DeleteMapping ("/unfollow/")
    public ResponseEntity<?> delete(@ModelAttribute FollowRequest followRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                String username = ((UserDetails) principal).getUsername();
                followRequest.setUserId(userService.findByUsername(username).getId().toString());
                followService.unfollow(followRequest);
                return ResponseEntity.status(200).body("Delete Successful");
                }
            }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
