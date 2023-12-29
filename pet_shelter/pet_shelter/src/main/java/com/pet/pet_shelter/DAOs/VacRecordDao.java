//package com.pet.pet_shelter.DAOs;
//
//import com.pet.pet_shelter.DTOs.Pet;
//import com.pet.pet_shelter.DTOs.Vaccination;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Component;
//
//@Component
//public class VacRecordDao {
//
//    @Autowired
//    JdbcTemplate jdbc;
//
//    void addVaccinationRecord(Pet pet, Vaccination vac){
//        jdbc.update("insert into pet_has_vaccination (pet_idpet,vaccination_vaccine) values(?,?)",
//                pet.getId(),
//                vac.getName());
//    }
//}
