package com.pet.pet_shelter.DAOs;

import com.pet.pet_shelter.DTOs.Staff;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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

    public boolean checkStaffIsAdmin(long id){
        List<Staff> res = jdbc.query("select is_admin from staff where staff.staff_id = ?",new StaffRowMapper() ,id);
        if(res.isEmpty()) return false;
        else{
            Optional<Staff> st = res.stream().findFirst();
            if(st.isEmpty()) return false;
            return true;
        }
    }

    static class StaffRowMapper implements RowMapper<Staff>{

        @Override
        public Staff mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Staff.builder()
            .id(rs.getInt("staff_id"))
            .firstname(rs.getString("first_name"))
            .lastName(rs.getString("last_name"))
            .shelterID(rs.getInt("shelter_id"))
            .isAdmin(rs.getBoolean("is_admin"))
            .phone(rs.getString("phone"))
            .email(rs.getString("email"))
            .password(rs.getString("password"))
            .build();
        }

    }
    
}
