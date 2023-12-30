package com.pet.pet_shelter.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pet.pet_shelter.DAOs.NotificationDao;
import com.pet.pet_shelter.DTOs.Notification;

@Service
public class NotificationService {
    @Autowired
    NotificationDao notificationDao;

    public List<Notification> getNotificationDetails(long userId){
        return notificationDao.getNotificationsData(userId);
    }

    public boolean removeNotification(long appId){
        if(notificationDao.removeNotification(appId)==1) return true;
        return false;
    }
}
