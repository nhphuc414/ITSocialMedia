package com.nhp.itsocialserver.controllers;

import com.nhp.itsocialserver.dtos.requests.UserRegisterRequest;
import com.nhp.itsocialserver.dtos.responses.ModelResponse;
import com.nhp.itsocialserver.dtos.responses.PostResponse;
import com.nhp.itsocialserver.dtos.responses.UserResponse;
import com.nhp.itsocialserver.mappers.PostMapper;
import com.nhp.itsocialserver.mappers.UserMapper;
import com.nhp.itsocialserver.pojos.Post;
import com.nhp.itsocialserver.pojos.User;
import com.nhp.itsocialserver.services.EmailService;
import com.nhp.itsocialserver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private EmailService emailService;
    @Autowired
    private PostMapper postMapper;

    @GetMapping("/current-user")
    public ResponseEntity<?> getMyAccount(Principal user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                String username = ((UserDetails) principal).getUsername();
                return ResponseEntity.ok(userMapper.toResponse(userService.findByUsername(username)));
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserInfo(@PathVariable int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            User user = userService.findById(id);
            if (user==null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
            return ResponseEntity.ok(userMapper.toResponse(user));
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
    @GetMapping("/{id}/timeline")
    public ResponseEntity<?> getUserTimeline(@PathVariable int id) {
        ModelResponse response;
        List<PostResponse> posts;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                String username = ((UserDetails) principal).getUsername();
                if (userService.findByUsername(username).getId()==id){
                    posts= userService.getUserProfileTimeLine(id,true).stream().map(p->postMapper.toResponse(p)).toList();
                } else {
                     posts= userService.getUserProfileTimeLine(id,false).stream().map(p->postMapper.toResponse(p)).toList();
                }
            if (posts.isEmpty()){
                response= new ModelResponse(200,"User Timeline","No Post");}
            else
            {
            response = new ModelResponse(200,"User Timeline",
                    posts);
            }
            return ResponseEntity.ok(response);
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
    @GetMapping("/isfollowing")
    public ResponseEntity<Boolean> isFollowing(@RequestParam int userId,@RequestParam int followingId){
        return new ResponseEntity<>(userService.isFollowing(userId,followingId),HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateInfo(@ModelAttribute UserRegisterRequest userRegisterRequest){
        ModelResponse response;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        int userId = userService.findByUsername(auth.getName()).getId();
        User updatedUser = userService.edit(userId, userRegisterRequest);
        if (updatedUser != null) {
            response = new ModelResponse(200, "Updated successful", userMapper.toResponse(updatedUser));
        }
        else {
            response = new ModelResponse(404, "User not found", null);
        }
        return ResponseEntity.ok(response);
    }
}
