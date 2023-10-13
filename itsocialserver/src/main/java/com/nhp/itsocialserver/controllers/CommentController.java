package com.nhp.itsocialserver.controllers;

import com.nhp.itsocialserver.dtos.responses.ModelResponse;
import com.nhp.itsocialserver.mappers.CommentMapper;
import com.nhp.itsocialserver.pojos.Comment;
import com.nhp.itsocialserver.services.CommentService;
import com.nhp.itsocialserver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/comment/", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE},
        consumes = {MediaType.MULTIPART_FORM_DATA_VALUE,MediaType.APPLICATION_JSON_VALUE})
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentMapper commentMapper;

    @GetMapping("/{id}/")
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
    @GetMapping("/{id}/comments/")
    public ResponseEntity<?> getComments(@PathVariable int id) {
        ModelResponse response;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            List<Comment> comments = commentService.getSubComment(id);
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
}
