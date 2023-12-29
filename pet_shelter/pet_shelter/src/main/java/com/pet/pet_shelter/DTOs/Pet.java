package com.pet.pet_shelter.DTOs;

import com.pet.pet_shelter.ENUMS.Gender;
import com.pet.pet_shelter.ENUMS.HouseTraining;
import com.pet.pet_shelter.ENUMS.NeuturingStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

@Builder
@Getter
@Setter
public class Pet {
    Long id;
    String name;
    String species;
    String breed;
    Date birthDate;
    int age;
    Gender gender;
    String healthStatus;
    String behavior;
    String description;
    HouseTraining houseTraining;
    Boolean neuturingStatus;
    Integer shelterID;
    Date joinDate;
}
