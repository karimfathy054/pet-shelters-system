package com.pet.pet_shelter.DAOs.DTOs;

import com.pet.pet_shelter.ENUMS.ApplicationStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class AdoptionApplication {
    long appId;
    ApplicationStatus status;
    long petID;
    long adopterId;
}