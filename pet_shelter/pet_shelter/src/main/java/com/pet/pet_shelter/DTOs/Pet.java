package com.pet.pet_shelter.DTOs;

import com.pet.pet_shelter.ENUMS.Gender;
import com.pet.pet_shelter.ENUMS.HouseTraining;
import lombok.*;

import java.sql.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pet {
    long petId;
    String name;
    String species;
    String breed;
    Date birthDate;
    Gender gender;
    String healthStatus;
    String behavior;
    String description;
    int shelterId;
    HouseTraining trainingStatus;
    boolean neuteringStatus;
    Date joinDate;
}
