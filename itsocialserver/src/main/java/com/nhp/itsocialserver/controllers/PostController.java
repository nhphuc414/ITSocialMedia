package com.nhp.itsocialserver.controllers;

import com.nhp.itsocialserver.dtos.requests.PostRequest;
import com.nhp.itsocialserver.dtos.requests.UserRegisterRequest;
import com.nhp.itsocialserver.dtos.responses.CommentResponse;
import com.nhp.itsocialserver.dtos.responses.ModelResponse;
import com.nhp.itsocialserver.dtos.responses.PostResponse;
import com.nhp.itsocialserver.dtos.responses.ReactionResponse;
import com.nhp.itsocialserver.mappers.CommentMapper;
import com.nhp.itsocialserver.mappers.PostMapper;
import com.nhp.itsocialserver.mappers.ReactionMapper;
import com.nhp.itsocialserver.pojos.Comment;
import com.nhp.itsocialserver.pojos.Post;
import com.nhp.itsocialserver.pojos.Reaction;
import com.nhp.itsocialserver.pojos.User;
import com.nhp.itsocialserver.services.PostService;
import com.nhp.itsocialserver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/post")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;
    @Autowired
    private PostMapper postMapper;
    @Autowired private CommentMapper commentMapper;
    @Autowired private ReactionMapper reactionMapper;
    @GetMapping("/{id}")
    public ResponseEntity<?> getPostInfo(@PathVariable int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Post post = postService.findById(id);
            if (post ==null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Post not found");
            ModelResponse response = new ModelResponse(200,"Post Info",
                    postMapper.toResponse(post));
            return ResponseEntity.ok(response);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
    @GetMapping("/{id}/comments")
    public ResponseEntity<?> getComments(@PathVariable int id) {
        ModelResponse response;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            List<CommentResponse> comments = postService.getComments(id).stream().map(c->commentMapper.toResponse(c)).toList();
            response = new ModelResponse(200,"Comments",
                    comments);
            return ResponseEntity.ok(response);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
    @GetMapping("/{id}/reactions")
    public ResponseEntity<?> getReaction(@PathVariable int id) {
        ModelResponse response;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            List<ReactionResponse> reactions = postService.getReactions(id).stream().map(r->reactionMapper.toResponse(r)).toList();
            response = new ModelResponse(200,"Reactions",
                    reactions);
            return ResponseEntity.ok(response);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
    @PostMapping("/add")
    public ResponseEntity<?> create(@ModelAttribute PostRequest postRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                String username = ((UserDetails) principal).getUsername();
            postRequest.setUserId(userService.findByUsername(username).getId().toString());
            postRequest.setStatus("PUBLIC");
                Post post = postService.create(postRequest);
                if (post == null) {
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("Post creation failed");
                }
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
    @PutMapping("/{id}/update")
    public ResponseEntity<?> updatePost(@PathVariable int id,@ModelAttribute PostRequest postRequest){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                String username = ((UserDetails) principal).getUsername();
               if(postService.findById(id).getUserId().getId().equals(userService.findByUsername(username).getId()))
            {
                Post post = postService.edit(id,postRequest);
                if (post == null) {
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("Post update failed");
                }
                ModelResponse response = new ModelResponse(200, "Updated successful", postMapper.toResponse(post));
                return ResponseEntity.ok(response);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
    @GetMapping("/get")
    public ResponseEntity<?> get(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            List<PostResponse> posts= postService.findAll().stream().map(p->postMapper.toResponse(p)).toList();
            return ResponseEntity.ok(posts);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
    @DeleteMapping ("/{id}/delete")
    public ResponseEntity<?> delete(@PathVariable int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                String username = ((UserDetails) principal).getUsername();
                if (authentication.getAuthorities().stream().anyMatch(a->a.getAuthority().equals("ADMIN")) ||postService.findById(id).getUserId().getId().equals(userService.findByUsername(username).getId()))
                {
                    postService.deleteById(id);
                    return ResponseEntity.status(200).body("Delete Successful");
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
