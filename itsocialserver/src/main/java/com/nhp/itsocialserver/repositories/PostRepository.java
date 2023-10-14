package com.nhp.itsocialserver.repositories;

import com.nhp.itsocialserver.pojos.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {
    List<Post> findAllByUserId_IdOrderByIdDesc(int userId);
    List<Post> findAllByCommunityId_Id(int id);

    void deleteById(int id);
}
