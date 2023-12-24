package com.pet.pet_shelter.DTOs;

import com.pet.pet_shelter.ENUMS.ApplicationStatus;
import lombok.Builder;

@Builder
public class AdoptionApplication {
    int id;
    ApplicationStatus status;
    int petID;
    int adopterID;
}
