package com.nhp.itsocialserver.repositories;

import com.nhp.itsocialserver.pojos.CommunityUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityUserRepository extends JpaRepository<CommunityUser,Integer> {

}
