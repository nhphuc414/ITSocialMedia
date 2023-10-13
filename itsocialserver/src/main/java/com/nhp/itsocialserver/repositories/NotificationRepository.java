package com.nhp.itsocialserver.repositories;

import com.nhp.itsocialserver.pojos.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Integer> {
}
