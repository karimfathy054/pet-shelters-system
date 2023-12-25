package com.pet.pet_shelter.DAOs;

import com.pet.pet_shelter.DTOs.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class PetDao {

    @Autowired
    JdbcTemplate jdbc;

    void addPet(Pet pet){
        jdbc.update("insert into pet (idpet,name,species,breed,date_of_birth,gender,health_status," +
                        "behavior,description,shelter_idshelter,house_training,neuturing_status) values (?,?,?,?,?,?,?,?,?,?,?,?)",
                pet.getId(),
                pet.getName(),
                pet.getSpecies(),
                pet.getBirthDate(),
                pet.getGender(),
                pet.getHealthStatus(),
                pet.getBehavior(),
                pet.getDescription(),
                pet.getShelterID(),
                pet.getTrainingStatus(),
                pet.getNeuturingStatus());
    }
}
