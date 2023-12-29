package com.pet.pet_shelter.DAOs;

import com.pet.pet_shelter.DAOs.DTOs.Shelter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class ShelterDao {

    @Autowired
    JdbcTemplate jdbc;

    void addShelter(Shelter shelter){
        jdbc.update("insert into shelter (name,location,phone,manager_id) values (?,?,?,?)",
                shelter.getName(),
                shelter.getLocation(),
                shelter.getPhoneNumber(),
                shelter.getManagerId());
    }

    Optional<Shelter> getShelterById(int id){
        List<Shelter> res = jdbc.query("select * from shelter where shelter.idshelter = ?", new ShelterRowMapper(),id);
        return res.stream().findFirst();
    }
    Optional<Shelter> getShelterByManager(int id){
        List<Shelter> res = jdbc.query("select * from shelter where shelter.manager_id = ?", new ShelterRowMapper(),id);
        return res.stream().findFirst();
    }

    static class ShelterRowMapper implements RowMapper<Shelter>{

        @Override
        public Shelter mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Shelter.builder()
            .id(rs.getInt("idshelter"))
            .name(rs.getString("name"))
            .location(rs.getString("location"))
            .phoneNumber(rs.getString("phone"))
            .managerId(rs.getInt("manager_id")).build();
        }

    }
}
