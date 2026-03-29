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

    @PostMapping
    public ResponseEntity<Notification> createNotification(@RequestBody Notification notification) {
        notification.setTimestamp(java.time.LocalDateTime.now());
        return ResponseEntity.ok(notificationRepository.save(notification));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Notification> updateNotification(@PathVariable String id, @RequestBody Notification notification) {
        return notificationRepository.findById(id).map(existing -> {
            existing.setTitle(notification.getTitle());
            existing.setMessage(notification.getMessage());
            existing.setRead(notification.isRead());
            return ResponseEntity.ok(notificationRepository.save(existing));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable String id) {
        if (notificationRepository.existsById(id)) {
            notificationRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
