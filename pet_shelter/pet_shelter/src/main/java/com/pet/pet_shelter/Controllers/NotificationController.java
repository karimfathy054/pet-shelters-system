package com.pet.pet_shelter.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.pet.pet_shelter.DTOs.Notification;
import com.pet.pet_shelter.Services.NotificationService;


@RestController
@RequestMapping("/notifications")
@CrossOrigin
public class NotificationController {
    @Autowired
    NotificationService notificationService;

    @GetMapping("/{userId}")
    public List<Notification> getNotifications(@PathVariable long userId) {
        return notificationService.getNotificationDetails(userId);
    }

    @DeleteMapping("/app_id={appId}")
    public boolean removeNotification(@PathVariable long appId) {
        return notificationService.removeNotification(appId);
    }
    
}
