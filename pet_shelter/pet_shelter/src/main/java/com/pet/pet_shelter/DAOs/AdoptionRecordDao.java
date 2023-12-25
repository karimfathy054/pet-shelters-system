package com.pet.pet_shelter.DAOs;

import com.pet.pet_shelter.DTOs.Adopter;
import com.pet.pet_shelter.DTOs.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class AdoptionRecordDao {
    @Autowired
    JdbcTemplate jdbc;

    void addRecord(Pet pet, Adopter adopter){
        jdbc.update("insert into adoption_record(adopter_idadopter,pet_idpet), values (?,?)",
                pet.getId(),
                adopter.getId());
    }
}
