//package com.pet.pet_shelter.DAOs;
//
//import com.pet.pet_shelter.DTOs.Adopter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Component;
//
//@Component
//public class AdopterDao {
//    @Autowired
//    private JdbcTemplate jdbc;
//
//    void addAdopter(Adopter adopter){
//        jdbc.update("insert into adopter (idadopter,adopter_firstname,adopter_lastname,adopter_address,adopter_phone) values(?,?,?,?,?)",
//                adopter.getId(),
//                adopter.getFirstName(),
//                adopter.getLastName(),
//                adopter.getAddress(),
//                adopter.getPhone());
//
//    }
//}
