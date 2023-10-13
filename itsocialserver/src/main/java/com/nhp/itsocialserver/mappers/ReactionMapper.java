package com.nhp.itsocialserver.mappers;

import com.nhp.itsocialserver.dtos.requests.ReactionRequest;
import com.nhp.itsocialserver.pojos.Reaction;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReactionMapper {
    @Autowired
    private ModelMapper mapper;

    public Reaction toModel(ReactionRequest reactionRequest){
        return mapper.map(reactionRequest,Reaction.class);
    }
}
