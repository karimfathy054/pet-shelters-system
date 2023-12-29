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

    public void addShelter(Shelter shelter){
        jdbc.update("insert into shelter (name,location,phone,manager_id) values (?,?,?,?) ;",
                shelter.getName(),
                shelter.getLocation(),
                shelter.getPhoneNumber(),
                shelter.getManagerId());
    }

    public Optional<Shelter> getShelterById(int id){
        List<Shelter> res = jdbc.query("select * from shelter where shelter.idshelter = ? ;", new ShelterRowMapper(),id);
        return res.stream().findFirst();
    }
    public Optional<Shelter> getShelterByManagerId(int id){
        List<Shelter> res = jdbc.query("select * from shelter where shelter.manager_id = ? ;", new ShelterRowMapper(),id);
        return res.stream().findFirst();
    }
    public Optional<Shelter> getShelterByManagerEmail(String email){
        List<Shelter> res = jdbc.query("""
            SELECT s.idshelter , s.name , s.location , s.phone , s.manager_id
            FROM shelter s
            JOIN (SELECT m.staff_id , m.email FROM staff m)
            ON s.manager_id = m.staff_id
            WHERE m.email = ?;
        """, new ShelterRowMapper(),email);
        return res.stream().findFirst();
    }

    public int deleteShelter(Integer shelterId){
        return jdbc.update("DELETE FROM shelter WHERE shelter.shelter_id = ?", shelterId);
    }


    static class ShelterRowMapper implements RowMapper<Shelter>{

        @Override
        public Shelter mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Shelter.builder()
            .id(rs.getInt("idshelter"))
            .name(rs.getString("name"))
            .location(rs.getString("location"))
            .phoneNumber(rs.getString("phone"))
            .managerId(rs.getInt("manager_id"))
            .build();
        }

    }
}
