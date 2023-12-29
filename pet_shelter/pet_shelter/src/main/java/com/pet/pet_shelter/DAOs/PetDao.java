package com.pet.pet_shelter.DAOs;

import com.pet.pet_shelter.DTOs.Pet;
import com.pet.pet_shelter.ENUMS.Gender;
import com.pet.pet_shelter.ENUMS.HouseTraining;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class PetDao {

    @Autowired
    JdbcTemplate jdbc;

    void addPet(Pet pet){
        jdbc.update("insert into pet (idpet,name,species,breed,date_of_birth,gender,health_status,behavior,description,house_training,neuturing_status,shelter_id,join_date) values (?,?,?,?,?,?,?,?,?,?,?,?,?)",
                pet.getId(),
                pet.getName(),
                pet.getSpecies(),
                pet.getBreed(),
                pet.getBirthDate(),
                pet.getGender(),
                pet.getHealthStatus(),
                pet.getBehavior(),
                pet.getDescription(),
                pet.getHouseTraining(),
                pet.getNeuturingStatus(),
                pet.getShelterID(),
                pet.getJoinDate()
                );
    }

    Optional<Pet> findPetByID(Long petId){
        List<Pet> res = jdbc.query("select * from pet where pet.idpet = ?", new PetRowMapper() , petId);
        return res.stream().findFirst();
    }

    List<Pet> findPetByName(String petName){
        return jdbc.query("select * from pet where pet.name Like(?)", new PetRowMapper() , petName);
    }
    
    List<Pet> findPetBySpecies(String species){
        return jdbc.query("select * from pet where pet.species Like(?)", new PetRowMapper() , species);
    }
    List<Pet> findPetByBreed(String breed){
        return jdbc.query("select * from pet where pet.breed Like(?)", new PetRowMapper() , breed);
    }

    List<Pet> findPetByAge(Date lowerBound,Date upperBound){
        return jdbc.query("select * from pet where pet.date_of_birth between ? and ?", new PetRowMapper() , lowerBound,upperBound);
    }
    List<Pet> findPetByHousetraining(HouseTraining training){
        return jdbc.query("select * from pet where pet.house_training = ?", new PetRowMapper() , training);
    }
    List<Pet> findPetByNeuturingStatus(Boolean neuturingStatus){
        return jdbc.query("select * from pet where pet.neuturing_status = ?", new PetRowMapper() , neuturingStatus);
    }

    List<Pet> findPetByLocation(String location){
        return jdbc.query("""
                SELECT p.idpet,p.name,p.species,p.breed,p.date_of_birth,p.gender,p.health_status,p.behavior,p.description,p.house_training,p.neuturing_status,p.shelter_id,p.join_date
                FROM pet p
                INNER JOIN (SELECT idshelter ,name ,location from shelter s)
                ON p.shelter_id = s.idshelter
                WHERE s.location = ?;
                """,new PetRowMapper(),location);
    }






    static class PetRowMapper implements RowMapper<Pet>{
        
        private int calculateAge(Date birthDate){
            return Period.between(birthDate.toLocalDate(),LocalDate.now()).getYears();
        }
        
        @Override
        public Pet mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Pet.builder()
            .id(rs.getLong("idpet"))
            .name(rs.getString("name"))
            .species(rs.getString("species"))
            .breed(rs.getString("breed"))
            .birthDate(rs.getDate("date_of_birth"))
            .gender(Gender.valueOf((rs.getString("gender"))))
            .healthStatus(rs.getString("health_status"))
            .behavior(rs.getString("behavior"))
            .description(rs.getString("description"))
            .houseTraining(HouseTraining.valueOf(rs.getString("house_training")))
            .neuturingStatus(rs.getBoolean("neuturing_status"))
            .shelterID(rs.getInt("shelter_id"))
            .joinDate(rs.getDate("join_date"))
            .age(calculateAge(rs.getDate("date_of_birth"))).build();
        }
        
    }
}
