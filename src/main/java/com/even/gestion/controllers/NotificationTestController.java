package com.even.gestion.controllers;

import com.even.gestion.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationTestController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/test/notifications")
    public String testNotifications() {
        notificationService.sendEventReminders();
        return "Notification test triggered!";
    }
}
