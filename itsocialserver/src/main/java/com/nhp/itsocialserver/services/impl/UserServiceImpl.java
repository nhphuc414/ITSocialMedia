package com.nhp.itsocialserver.services.impl;

import com.nhp.itsocialserver.dtos.requests.UserRegisterRequest;
import com.nhp.itsocialserver.mappers.UserMapper;
import com.nhp.itsocialserver.pojos.*;
import com.nhp.itsocialserver.repositories.FollowRepository;
import com.nhp.itsocialserver.repositories.UserRepository;
import com.nhp.itsocialserver.services.CloudinaryService;
import com.nhp.itsocialserver.services.UserService;
import com.nhp.itsocialserver.utils.Utils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final CloudinaryService cloudinaryService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final Utils utils;
    private final FollowRepository followRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, CloudinaryService cloudinaryService, UserMapper userMapper, PasswordEncoder passwordEncoder, Utils utils, FollowRepository followRepository) {
        this.userRepository = userRepository;
        this.cloudinaryService = cloudinaryService;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.utils = utils;
        this.followRepository = followRepository;
    }

    @Override
    public User findById(int id) {
        return userRepository.findById(id).orElse(null);
    }
    @Override
    public User findByUsername(String username) {
        List<User> users = userRepository.findByUsername(username);
        if (users.isEmpty())
            return null;
        else
            return users.get(0);
    }



    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User create(UserRegisterRequest userRegisterRequest) {
        User user = userMapper.toModel(userRegisterRequest);
        user.setPassword(passwordEncoder.encode(userRegisterRequest.getPassword()));
        if (utils.isNotEmptyFile(userRegisterRequest.getAvatarFile() )){
            user.setImage(cloudinaryService.uploadImage(userRegisterRequest.getAvatarFile()));
        } else{
            if (userRegisterRequest.getAvatar()!=null &&
                    !userRegisterRequest.getAvatar().isEmpty() &&
            !userRegisterRequest.getAvatar().isBlank()){
                user.setImage(userRegisterRequest.getAvatar());
            } else user.setImage("https://res.cloudinary.com/dm5nn54wh/image/upload/v1697202418/qkhz0wz7getw9vqvex2w.jpg");
        }
        if (utils.isNotEmptyFile(userRegisterRequest.getBgImageFile() )){
            user.setBgImage(cloudinaryService.uploadImage(userRegisterRequest.getBgImageFile()));
        } else user.setBgImage("https://res.cloudinary.com/dm5nn54wh/image/upload/v1697202421/rveknrmarmysphqdeahm.jpg");
        user.setRole("USER");
        user.setCreatedDate(new Date());
        return this.userRepository.saveAndFlush(user);
    }
    @Override
    public User edit(int id, UserRegisterRequest userRegisterRequest) {
        User user = this.findById(id);
        if (utils.isNotEmpty(userRegisterRequest.getFullName())){
            user.setFullName(userRegisterRequest.getFullName());
        }
        if (utils.isNotEmpty(userRegisterRequest.getEmail())){
            user.setEmail(userRegisterRequest.getEmail());
        }
        if (utils.isNotEmpty(userRegisterRequest.getPassword())){
            user.setPassword(passwordEncoder.encode(userRegisterRequest.getPassword()));
        }
        if (utils.isNotEmptyFile(userRegisterRequest.getAvatarFile() )){
            user.setImage(cloudinaryService.uploadImage(userRegisterRequest.getAvatarFile()));
        }
        if (utils.isNotEmptyFile(userRegisterRequest.getAvatarFile() )){
            user.setBgImage(cloudinaryService.uploadImage(userRegisterRequest.getBgImageFile()));
        }
        user.setUpdatedDate(new Date());
        return this.userRepository.saveAndFlush(user);
    }

    @Override
    public List<Post> getUserProfileTimeLine(int id, boolean isMyProfile) {
        if (isMyProfile){
            return this.findById(id).getPostList();
        }
        return findById(id).getPostList().stream().filter(p->!p.getStatus().equals("HIDDEN")).toList();
    }
    @Override
    public List<Community> getUserCommunities(int id) {
        return findById(id).getCommunityUserList().stream().map(CommunityUser::getCommunityId).toList();
    }
    @Override
    public void deleteById(int id) {
        this.userRepository.deleteById(id);
    }
    @Override
    public boolean existsByUsername(String username) {
        return this.userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return this.userRepository.existsByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.findByUsername(username);
        if (user!=null){
            List<GrantedAuthority> auth = new ArrayList<>();
            auth.add(new SimpleGrantedAuthority(user.getRole()));
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    auth
        );
        }
        return null;
    }
}
