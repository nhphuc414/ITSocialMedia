package com.nhp.itsocialserver.repositories;

import com.nhp.itsocialserver.pojos.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunityRepository extends JpaRepository<Community,Integer> {
    Community findById(int id);

}
