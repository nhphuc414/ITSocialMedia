package com.nhp.itsocialserver.repositories;

import com.nhp.itsocialserver.pojos.NotificationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationUserRepository extends JpaRepository<NotificationUser,Integer> {
        List<NotificationUser> getAllByUserId_Id(int id);
        List<NotificationUser> getAllByNotificationId_Id(int id);
}
