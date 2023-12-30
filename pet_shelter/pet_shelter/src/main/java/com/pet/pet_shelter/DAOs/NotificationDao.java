package com.pet.pet_shelter.DAOs;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.pet.pet_shelter.DTOs.Notification;
import com.pet.pet_shelter.ENUMS.ApplicationStatus;
import com.pet.pet_shelter.ENUMS.Gender;

@Component
public class NotificationDao {
    @Autowired
    JdbcTemplate jdbc;

    public void addNotification(Notification notification){
        jdbc.update("insert into app_notify (app_id,adopter_id,not_time) values(?,?,?)",
        notification.getAppId(),
        notification.getAdopterId(),
        notification.getNotificationTime()
        );
    }

    public List<Notification> getNotificationsforUser(Long userId){
        return jdbc.query("select * from app_notify where app_notify.adopter_id = ?", new NotificationRowMapper(), userId);
    }

    public List<Notification> getNotificationsData(Long userId){
        return jdbc.query("""
            Select n.app_id ,n.not_time , a.status , p.name , p.species , p.breed , p.date_of_birth , p.gender , s.location
            FROM app_notify n
            JOIN (SELECT status ,pet_id , app_id FROM adoption_application) AS a
            ON n.app_id = a.app_id
            JOIN (SELECT idpet ,name ,species ,breed ,date_of_birth ,gender ,shelter_id FROM pet ) AS p
            ON a.pet_id = p.idpet
            Join (Select idshelter ,location FROM shelter ) AS s
            ON p.shelter_id = s.idshelter
            WHERE n.adopter_id = ? ;
        """,new RowMapper<Notification>(){
            private int calculateAge(Date birthDate){
            return Period.between(birthDate.toLocalDate(),LocalDate.now()).getYears();
            }
            @Override
            public Notification mapRow(ResultSet rs, int rowNum) throws SQLException {
                return Notification.builder()
                .appId(rs.getLong("app_id"))
                .notificationTime(rs.getTimestamp("not_time"))
                .status(ApplicationStatus.valueOf(rs.getString("status")))
                .petName(rs.getString("name"))
                .species(rs.getString("species"))
                .breed(rs.getString("breed"))
                .petAge(calculateAge(rs.getDate("date_of_birth")))
                .gender(Gender.valueOf(rs.getString("gender")))
                .location(rs.getString("location"))
                .build();
            }
        } ,userId);
    }

    public int removeNotification(long appId){
        return jdbc.update("DELETE FROM app_notify WHERE app_notify.app_id = ?", appId);
    }


    static class NotificationRowMapper implements RowMapper<Notification>{

        @Override
        public Notification mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Notification.builder()
            .adopterId(rs.getLong("adopter_id"))
            .appId(rs.getLong("app_id"))
            .notificationTime(rs.getTimestamp("not_time")).build();
        }

    }
}
