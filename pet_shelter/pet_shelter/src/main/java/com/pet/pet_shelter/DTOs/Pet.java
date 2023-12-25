package com.pet.pet_shelter.DTOs;

import com.pet.pet_shelter.ENUMS.Gender;
import com.pet.pet_shelter.ENUMS.HouseTraining;
import com.pet.pet_shelter.ENUMS.NeuturingStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Builder
@Getter
@Setter
public class Pet {
    int id;
    String name;
    String species;
    String breed;
    Date birthDate;
    Gender gender;
    String healthStatus;
    String behavior;
    String description;
    int shelterID;
    HouseTraining trainingStatus;
    NeuturingStatus neuturingStatus;

}
