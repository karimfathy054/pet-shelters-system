package com.pet.pet_shelter.DAOs;

import com.pet.pet_shelter.DAOs.DTOs.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class StaffDao {

    @Autowired
    JdbcTemplate jdbc;

    void addStaffMember(Staff member){
        jdbc.update("""
                insert into staff (first_name,last_name,shelter_id,is_admin,phone,email,password)
                values(?,?,?,?,?,?,?)
                """,
                member.getFirstname(),
                member.getLastName(),
                member.getShelterID(),
                member.getIsAdmin(),
                member.getPhone(),
                member.getEmail(),
                member.getPassword()
                );
    }

    
}
