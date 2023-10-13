package com.nhp.itsocialserver.repositories;

import com.nhp.itsocialserver.pojos.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {
    void deleteById(int id);
}
