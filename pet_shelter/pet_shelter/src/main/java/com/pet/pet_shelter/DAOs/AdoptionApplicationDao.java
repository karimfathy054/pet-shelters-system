package com.pet.pet_shelter.DAOs;

import com.pet.pet_shelter.DTOs.AdoptionApplication;
import com.pet.pet_shelter.ENUMS.ApplicationStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class AdoptionApplicationDao {

    @Autowired
    JdbcTemplate jdbc;

    void addApplication(AdoptionApplication app){
        jdbc.update("insert into adoption_application (app_id,adopter_firstname,adopter_lastname,adopter_phone,"
        +"adopter_address,status,pet_id,adopter_id)",
                app.getId(),
                app.getAdopterFirstName(),
                app.getAdopterLastName(),
                app.getAdopterPhone(),
                app.getAdopterAddress(),
                app.getStatus(),
                app.getPetID(),
                app.getAdopterID()
                );
    }

    public Optional<AdoptionApplication> getApplicationById(Long id){
        List<AdoptionApplication> res = jdbc.query("select * from adoption_application where adoption_application.app_id = ?",new AdoptionAppRowMapper(),id);
        return res.stream().findFirst();
    }
    public List<AdoptionApplication> getApplicationsByPetId(Long id){
        return jdbc.query("select * from adoption_application where adoption_application.pet_id = ?",new AdoptionAppRowMapper(),id);
    }
    public List<AdoptionApplication> getApplicationsByAdopterId(Long id){
        return jdbc.query("select * from adoption_application where adoption_application.adopter_id = ?",new AdoptionAppRowMapper(),id);
    }

    static class AdoptionAppRowMapper implements RowMapper<AdoptionApplication>{

        @Override
        public AdoptionApplication mapRow(ResultSet rs, int rowNum) throws SQLException {
            return AdoptionApplication.builder()
            .adopterAddress(rs.getString("adopter_address"))
            .adopterFirstName(rs.getString("adopter_firstname"))
            .adopterID(rs.getLong("adopter_id"))
            .adopterLastName(rs.getString("adopter_lastname"))
            .adopterPhone(rs.getString("adopter_phone"))
            .id(rs.getLong("app_id"))
            .status(Enum.valueOf(ApplicationStatus.class,rs.getString("status")))
            .petID(rs.getLong("pet_id"))
            .build();
        }
    }

}
