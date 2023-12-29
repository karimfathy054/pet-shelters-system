package com.pet.pet_shelter.DAOs;

import com.pet.pet_shelter.DTOs.Adopter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class AdopterDao {
    @Autowired
    private JdbcTemplate jdbc;

    void addAdopter(Adopter adopter){
        jdbc.update("insert into adopter (email,password,join_date,adopter_id,firstName,secondName,phone,address) values(?,?,?,?,?,?,?,?))",
                adopter.getEmail(),
                adopter.getPassword(),
                adopter.getJoinDate(),
                adopter.getId(),
                adopter.getFirstName(),
                adopter.getSecondName(),
                adopter.getPhone(),
                adopter.getAddress()
                
                );

    }

    public Optional<Adopter> getAdopterByID(Long id){
        List<Adopter>res =  jdbc.query("select username , email ,password ,create_time , adopter_id from adopter where adopter.adopter_id =?",new AdopterRowMapper(),id );
        return res.stream().findFirst();
    }

    public Optional<Adopter> getAdopterBy(String email){
        List<Adopter>res = jdbc.query("select username , email ,password ,create_time , adopter_id from adopter where adopter.email =?",new AdopterRowMapper(),email );
        return res.stream().findFirst();
    }

    static class AdopterRowMapper implements RowMapper<Adopter>{

        @Override
        public Adopter mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Adopter.builder()
            .email(rs.getString("email"))
            .password(rs.getString("password"))
            .joinDate(rs.getTimestamp("create_time"))
            .id(rs.getLong("adopter_id"))
            .firstName(rs.getString("firstName"))
            .secondName(rs.getString("secondName"))
            .phone(rs.getString("phone"))
            .address(rs.getString("address"))
            .build();
        }

    }
}
