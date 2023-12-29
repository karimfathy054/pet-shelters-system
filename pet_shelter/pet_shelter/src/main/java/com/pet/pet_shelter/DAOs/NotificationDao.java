package com.pet.pet_shelter.DAOs;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.pet.pet_shelter.DTOs.Notification;

@Component
public class NotificationDao {
    @Autowired
    JdbcTemplate jdbc;

    void addNotification(Notification notification){
        jdbc.update("insert into app_notify (adopter_id,app_id,date) values(?,?,?)",
        notification.getAdopterId(),
        notification.getAppId(),
        notification.getDate() );
    }

    public List<Notification> getNotificationsforUser(Long userId){
        return jdbc.query("select * from app_notify where app_notify.adopter_id = ?", new NotificationRowMapper(), userId);
    }


    static class NotificationRowMapper implements RowMapper<Notification>{

        @Override
        public Notification mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Notification.builder()
            .adopterId(rs.getLong("adopter_id"))
            .appId(rs.getLong("app_id"))
            .date(rs.getDate("date")).build();
        }

    }
}
