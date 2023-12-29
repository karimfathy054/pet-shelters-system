//package com.pet.pet_shelter.DAOs;
//
//import com.pet.pet_shelter.DTOs.AdoptionApplication;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Component;
//
//@Component
//public class AdoptionApplicationDao {
//
//    @Autowired
//    JdbcTemplate jdbc;
//
//    void addApplication(AdoptionApplication app){
//        jdbc.update("insert into adoption_application (adopter_idadopter,idadoption_application" +
//                        ",pet_idpet,status) values (?,?,?,?,?)",
//                app.getAdopterID(),
//                app.getId(),
//                app.getPetID(),
//                app.getStatus());
//    }
//
//}
