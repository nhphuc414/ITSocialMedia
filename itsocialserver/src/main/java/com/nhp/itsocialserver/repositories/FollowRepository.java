package com.nhp.itsocialserver.repositories;

import com.nhp.itsocialserver.pojos.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Integer> {
    Optional<Follow> findByUserId_IdAndFollowingId_Id(int userId, int followingId);
    List<Follow> findAllByUserId_Id(int userId);
}
