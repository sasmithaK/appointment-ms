package com.ctse.notification_service.controller;

import com.ctse.notification_service.model.Notification;
import com.ctse.notification_service.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationRepository notificationRepository;

    @GetMapping("/user/{recipientId}")
    public ResponseEntity<List<Notification>> getUserNotifications(@PathVariable String recipientId) {
        return ResponseEntity.ok(notificationRepository.findByRecipientIdOrderByTimestampDesc(recipientId));
    }
}
