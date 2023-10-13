package com.nhp.itsocialserver.services.impl;

import com.nhp.itsocialserver.dtos.requests.CommunityRequest;
import com.nhp.itsocialserver.mappers.CommunityMapper;
import com.nhp.itsocialserver.pojos.*;
import com.nhp.itsocialserver.repositories.CommunityRepository;
import com.nhp.itsocialserver.services.CommunityService;
import com.nhp.itsocialserver.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class CommunityServiceImpl implements CommunityService {
    @Autowired
    private CommunityRepository communityRepository;
    @Autowired
    private CommunityMapper communityMapper;

    @Override
    public Community getById(int id) {
        return communityRepository.findById(id).orElse(null);
    };


    @Override
    public List<User> getMembers(int id) {
        return new ArrayList<>(this.getById(id).getCommunityUserList().stream().map(CommunityUser::getUserId).toList());
    }

    @Override
    public List<Post> getCommunityTimeLine(int id) {
        List<Post> timeLine = new ArrayList<>(this.getById(id).getPostList());
        Collections.reverse(timeLine);
        return timeLine;
    }

    @Override
    public Community create(CommunityRequest communityRequest) {
        return null;
    }

    @Override
    public Community edit(CommunityRequest communityRequest) {
        return null;
    }

    @Override
    public void deleteById(int id) {
        communityRepository.deleteById(id);
    }
}
