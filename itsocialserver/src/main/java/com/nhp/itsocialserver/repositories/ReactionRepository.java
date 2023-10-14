package com.nhp.itsocialserver.repositories;

import com.nhp.itsocialserver.pojos.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction,Integer> {
    Optional<Reaction> findByUserId_IdAndPostId_Id(int userId,int postId);
    List<Reaction> findAllByPostId_Id(int postId);
    List<Reaction> findAllByUserId_Id(int userId);
    void deleteLikeById(int id);
}
