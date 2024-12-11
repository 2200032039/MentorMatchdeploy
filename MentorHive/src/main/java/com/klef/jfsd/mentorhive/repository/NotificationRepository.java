package com.klef.jfsd.mentorhive.repository;

import com.klef.jfsd.mentorhive.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
