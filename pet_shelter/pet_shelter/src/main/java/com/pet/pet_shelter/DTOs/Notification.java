package com.pet.pet_shelter.DTOs;


import java.sql.Date;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pet.pet_shelter.ENUMS.ApplicationStatus;
import com.pet.pet_shelter.ENUMS.Gender;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Notification {

    Long appId;

    @JsonIgnore
    Long adopterId;
    
    Timestamp notificationTime;
    ApplicationStatus status;
    String petName;
    String species;
    String breed;
    int petAge;
    Gender gender;
    String location;
}
