package com.ctse.notification_service.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "notifications")
public class Notification {
    @Id
    private String id;
    private String recipientId; // Who the notification is for
    private String title;
    private String message;
    private LocalDateTime timestamp;
    private boolean isRead;
}
