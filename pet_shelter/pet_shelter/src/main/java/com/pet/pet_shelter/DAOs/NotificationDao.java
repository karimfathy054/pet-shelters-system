package com.pet.pet_shelter.DAOs;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.pet.pet_shelter.DAOs.DTOs.Notification;

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
