package com.pet.pet_shelter.DAOs.DTOs;


import java.sql.Date;
import java.sql.Timestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Notification {
    Long appId;
    Long adopterId;
    Timestamp notificationTime;
}
