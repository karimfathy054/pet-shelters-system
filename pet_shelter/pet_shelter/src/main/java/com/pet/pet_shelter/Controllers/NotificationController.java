package com.pet.pet_shelter.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pet.pet_shelter.DTOs.Notification;
import com.pet.pet_shelter.Services.NotificationService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/notifications")
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
