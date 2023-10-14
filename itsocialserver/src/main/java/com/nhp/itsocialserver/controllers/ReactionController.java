package com.nhp.itsocialserver.controllers;

import com.nhp.itsocialserver.dtos.requests.PostRequest;
import com.nhp.itsocialserver.dtos.requests.ReactionRequest;
import com.nhp.itsocialserver.dtos.responses.ModelResponse;
import com.nhp.itsocialserver.mappers.PostMapper;
import com.nhp.itsocialserver.mappers.ReactionMapper;
import com.nhp.itsocialserver.pojos.Post;
import com.nhp.itsocialserver.pojos.Reaction;
import com.nhp.itsocialserver.services.PostService;
import com.nhp.itsocialserver.services.ReactionService;
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
@RequestMapping(value = "/api/reaction", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE},
        consumes = {MediaType.MULTIPART_FORM_DATA_VALUE,MediaType.APPLICATION_JSON_VALUE})
public class ReactionController {
    @Autowired
    private ReactionService reactionService;
    @Autowired
    private UserService userService;
    @Autowired
    private ReactionMapper reactionMapper;
    @Autowired
    private  PostService postService;
    @GetMapping("/{id}")
    public ResponseEntity<?> getInfo(@PathVariable int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Reaction reaction = reactionService.findById(id);
            if (reaction ==null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Reaction not found");
            ModelResponse response = new ModelResponse(200,"Reaction Info",
                    reactionMapper.toResponse(reaction));
            return ResponseEntity.ok(response);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
    @PostMapping("/add")
    public ResponseEntity<?> create(@ModelAttribute ReactionRequest reactionRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                String username = ((UserDetails) principal).getUsername();
                reactionRequest.setUserId(userService.findByUsername(username).getId().toString());
                Reaction reaction = reactionService.create(reactionRequest);
                if (reaction == null) {
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("Reaction creation failed");
                }
                return  ResponseEntity.status(HttpStatus.CREATED).body("Created Successfull");
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
    @GetMapping("/isliked")
    public ResponseEntity<Boolean> isReaction(@RequestParam int userId,@RequestParam int postId){
        return new ResponseEntity<>(reactionService.isReaction(userId,postId),HttpStatus.OK);
    }
    @DeleteMapping ("/{id}/")
    public ResponseEntity<?> delete(@PathVariable int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                String username = ((UserDetails) principal).getUsername();
                if (authentication.getAuthorities().stream().anyMatch(a->a.getAuthority().equals("ADMIN")) ||
                        reactionService.findById(id).getUserId().getId().equals(userService.findByUsername(username).getId()))
                {
                    reactionService.deleteById(id);
                    return ResponseEntity.status(200).body("Delete Successful");
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

}
