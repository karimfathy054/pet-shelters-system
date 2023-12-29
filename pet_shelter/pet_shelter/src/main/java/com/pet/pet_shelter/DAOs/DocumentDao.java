//package com.pet.pet_shelter.DAOs;
//
//import com.pet.pet_shelter.DTOs.Document;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Component;
//
//@Component
//public class DocumentDao {
//
//    @Autowired
//    JdbcTemplate jdbc;
//
//    void addDoc(Document doc){
//        jdbc.update("insert into documents (pet_idpet,path,type) values (?,?,?)",
//                doc.getPetID(),
//                doc.getPath(),
//                doc.getType());
//    }
//
//}
