package com.nhp.itsocialserver.services;

import com.nhp.itsocialserver.dtos.requests.UserRegisterRequest;
import com.nhp.itsocialserver.pojos.Community;
import com.nhp.itsocialserver.pojos.Post;
import com.nhp.itsocialserver.pojos.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    User findById(int id);
    User findByUsername(String username);
    boolean isFollowing(int userId,int followingId);
    User findByEmail(String email);
    User create(UserRegisterRequest userRegisterRequest);
    User edit(int id, UserRegisterRequest userRegisterRequest);
    List<Post> getUserProfileTimeLine(int id,boolean isMyProfile);
    List<Community> getUserCommunities(int id);
    void deleteById(int id);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);


}
