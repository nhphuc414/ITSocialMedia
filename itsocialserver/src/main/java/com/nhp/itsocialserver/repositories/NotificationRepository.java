package com.nhp.itsocialserver.repositories;

import com.nhp.itsocialserver.pojos.Notification;
import com.nhp.itsocialserver.pojos.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Integer> {
        List<Notification> getAllByUserId_Id(int userId);
}
