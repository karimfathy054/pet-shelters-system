//package com.pet.pet_shelter.DAOs;
//
//import com.pet.pet_shelter.DTOs.Staff;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Component;
//
//@Component
//public class StaffDao {
//
//    @Autowired
//    JdbcTemplate jdbc;
//
//    void addStaffMember(Staff member){
//        jdbc.update("insert into staff (idstaff,first_name,last_name,role,shelter_idshelter) values(?,?,?,?,?)",
//                member.getId(),
//                member.getFirstname(),
//                member.getLastName(),
//                member.getRole(),
//                member.getShelterID());
//    }
//}
