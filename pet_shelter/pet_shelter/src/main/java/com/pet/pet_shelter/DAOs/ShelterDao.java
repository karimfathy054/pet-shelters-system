package com.pet.pet_shelter.DAOs;

import com.pet.pet_shelter.DTOs.Shelter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ShelterDao {

    @Autowired
    JdbcTemplate jdbc;

    void addShelter(Shelter shelter){
        jdbc.update("insert into shelter (idshelter,location,name,phone) values (?,?,?,?)",
                shelter.getId(),
                shelter.getLocation(),
                shelter.getName(),
                shelter.getPhoneNumber());
    }
}
