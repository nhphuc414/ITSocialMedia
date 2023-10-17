package com.nhp.itsocialserver.controllers;

import com.nhp.itsocialserver.dtos.requests.CommentRequest;
import com.nhp.itsocialserver.dtos.requests.PostRequest;
import com.nhp.itsocialserver.dtos.responses.CommentResponse;
import com.nhp.itsocialserver.dtos.responses.ModelResponse;
import com.nhp.itsocialserver.dtos.responses.ReactionResponse;
import com.nhp.itsocialserver.mappers.CommentMapper;
import com.nhp.itsocialserver.mappers.ReactionMapper;
import com.nhp.itsocialserver.pojos.Comment;
import com.nhp.itsocialserver.pojos.Post;
import com.nhp.itsocialserver.services.CommentService;
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
@RequestMapping(value = "/api/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private ReactionMapper reactionMapper;

    @GetMapping("/{id}")
    public ResponseEntity<?> getInfo(@PathVariable int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Comment comment = commentService.findById(id);
            if (comment ==null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Comment not found");
            ModelResponse response = new ModelResponse(200,"Comment Info",
                    commentMapper.toResponse(comment));
            return ResponseEntity.ok(response);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
    @GetMapping("/{id}/comments")
    public ResponseEntity<?> getComments(@PathVariable int id) {
        ModelResponse response;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            List<CommentResponse> comments = commentService.getSubComment(id).stream().map(c->commentMapper.toResponse(c)).toList();
            if (comments.isEmpty()){
                response = new ModelResponse(200,"Comments",
                        "No sub comments");
            }
            else {
                response = new ModelResponse(200,"Sub Comments",
                        comments);
            }
            return ResponseEntity.ok(response);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
    @GetMapping("/{id}/reactions")
    public ResponseEntity<?> getReaction(@PathVariable int id) {
        ModelResponse response;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            List<ReactionResponse> reactions = commentService.getReactions(id).stream().map(r->reactionMapper.toResponse(r)).toList();
            if (reactions.isEmpty()){
                response = new ModelResponse(200,"Reactions",
                        0);
            }
            else {
                response = new ModelResponse(200,"Reactions",
                        reactions);
            }
            return ResponseEntity.ok(response);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
    @PostMapping("/add")
    public ResponseEntity<?> create(@ModelAttribute CommentRequest commentRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                String username = ((UserDetails) principal).getUsername();
                commentRequest.setUserId(userService.findByUsername(username).getId().toString());
                commentRequest.setStatus("PUBLIC");
                Comment comment = commentService.create(commentRequest);
                if (comment == null) {
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("Post creation failed");
                }
                return new ResponseEntity<>(commentMapper.toResponse(comment), HttpStatus.CREATED);
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
    @PutMapping("{id}/update")
    public ResponseEntity<?> update(@PathVariable int id,@ModelAttribute CommentRequest commentRequest){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                String username = ((UserDetails) principal).getUsername();
                if(commentService.findById(id).getPostId().getUserId().getId().equals(userService.findByUsername(username).getId())
                ||commentService.findById(id).getUserId().getId().equals(userService.findByUsername(username).getId()))
                {
                    Comment comment = commentService.edit(id,commentRequest);
                    if (comment == null) {
                        return ResponseEntity.status(HttpStatus.CONFLICT).body("Post update failed");
                    }
                    ModelResponse response = new ModelResponse(200, "Updated successful", commentMapper.toResponse(comment));
                    return ResponseEntity.ok(response);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
    @DeleteMapping ("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                String username = ((UserDetails) principal).getUsername();
                if (authentication.getAuthorities().stream().anyMatch(a->a.getAuthority().equals("ADMIN"))
                        ||commentService.findById(id).getPostId().getUserId().getId().equals(userService.findByUsername(username).getId())
                        ||commentService.findById(id).getUserId().getId().equals(userService.findByUsername(username).getId()))
                {
                    commentService.deleteById(id);
                    return ResponseEntity.status(200).body("Delete Successful");
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
