package com.pet.pet_shelter.DAOs;

import com.pet.pet_shelter.DTOs.AdoptionRecord;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class AdoptionRecordDao {
    @Autowired
    JdbcTemplate jdbc;

    void addRecord(AdoptionRecord adoptionRecord){
        jdbc.update("insert into adoption_record(adopting_family,pet_id), values (?,?)",
                adoptionRecord.getAdoptingFamily(),
                adoptionRecord.getPetId());
    }

    public List<AdoptionRecord> getRecordsByPet(Long petId){
        return jdbc.query("select adopting_family , pet_id from adoption_record where adoption_record.pet_id = ?", new AdoptionRecordRowMapper(),petId);
    }

    

    static class AdoptionRecordRowMapper implements RowMapper<AdoptionRecord>{

        @Override
        public AdoptionRecord mapRow(ResultSet rs, int rowNum) throws SQLException {
            return AdoptionRecord.builder()
            .adoptingFamily(rs.getString("adopting_family"))
            .petId(rs.getLong("pet_id")).build();
        }
        
    }
}
