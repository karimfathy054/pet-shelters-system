package com.pet.pet_shelter.DTOs;

import com.pet.pet_shelter.ENUMS.ApplicationStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class AdoptionApplication {
    Long id;
    String adopterFirstName;
    String adopterLastName;
    String adopterPhone;
    String adopterAddress;
    ApplicationStatus status;
    Long petID;
    Long adopterID;
}
