package com.pet.pet_shelter.DAOs.DTOs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class VaccinationRecord {
    String vaccine;
    Long petId;
}
