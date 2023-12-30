package com.pet.pet_shelter.DAOs;

import com.pet.pet_shelter.DTOs.VaccinationRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class VacRecordDao {

    @Autowired
    JdbcTemplate jdbc;

    void addVaccinationRecord(VaccinationRecord vac){
        jdbc.update("insert into vaccinated (vaccine,pet_id) values(?,?)",
                vac.getVaccine(),
                vac.getPetId());
    }
}
