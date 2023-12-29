package com.pet.pet_shelter.Services;


import com.pet.pet_shelter.DTOs.Adopter;
import com.pet.pet_shelter.DTOs.AdoptionApplication;
import com.pet.pet_shelter.ENUMS.ApplicationStatus;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@Service
public class AdopterService {
    private Connection conn;
    private String username = "root";
    private String password = "password";
    private String url = "jdbc:mysql://localhost:3306/mydb";
    AdopterService() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(url, username, password);
    }

    public Adopter signIn(String email, String password) {
        String getUserQuery = "SELECT * FROM adopter WHERE email = '"+ email +
                "' AND password = '" + password + "';";
        try{
            ResultSet resultSet = conn.prepareStatement(getUserQuery).executeQuery();
            if(resultSet.next()){
                return Adopter.builder()
                        .adopterId(resultSet.getInt("adopter_id"))
                        .phone(resultSet.getString("phone"))
                        .address(resultSet.getString("address"))
                        .firstName(resultSet.getString("firstName"))
                        .secondName(resultSet.getString("secondName"))
                        .joinDate(resultSet.getDate("join_date"))
                        .email(resultSet.getString("email"))
                        .password(resultSet.getString("password"))
                        .build();
            }
            return null;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public String signUp(Adopter adopter) {
        String addStaff = String.format(
                "INSERT INTO " +
                        "adopter(firstName, secondName, phone, email, password, address) " +
                        "VALUES('%s', '%s', '%s', '%s', '%s', '%s');"
                , adopter.getFirstName(), adopter.getSecondName(), adopter.getPhone()
                , adopter.getEmail(), adopter.getPassword(), adopter.getAddress()
        );
        System.out.println("Add Adopter QUERY: " + addStaff);
        try{
            conn.prepareStatement(addStaff).execute();
            return "Adopter Created!!";
        }catch (SQLException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public Adopter getByEmail(String email) {
        String getAdopterQuery = "SELECT * FROM adopter WHERE email = '"+ email + "';";
        try{
            ResultSet resultSet = conn.prepareStatement(getAdopterQuery).executeQuery();
            if(resultSet.next()){
                return Adopter.builder()
                        .email(resultSet.getString("email"))
                        .password(resultSet.getString("password"))
                        .joinDate(resultSet.getDate("join_date"))
                        .adopterId(resultSet.getInt("adopter_id"))
                        .firstName(resultSet.getString("firstName"))
                        .secondName(resultSet.getString("secondName"))
                        .phone(resultSet.getString("phone"))
                        .address(resultSet.getString("address"))
                        .build();
            }
            return null;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    public Adopter getById(long id) {
        String getAdopterQuery = "SELECT * FROM adopter WHERE adopter_id = "+ id + ";";
        try{
            ResultSet resultSet = conn.prepareStatement(getAdopterQuery).executeQuery();
            if(resultSet.next()){
                return Adopter.builder()
                        .email(resultSet.getString("email"))
                        .password(resultSet.getString("password"))
                        .joinDate(resultSet.getDate("join_date"))
                        .adopterId(resultSet.getInt("adopter_id"))
                        .firstName(resultSet.getString("firstName"))
                        .secondName(resultSet.getString("secondName"))
                        .phone(resultSet.getString("phone"))
                        .address(resultSet.getString("address"))
                        .build();
            }
            return null;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    public List<AdoptionApplication> getAllApplications(long id) {
        Adopter adopter = getById(id);
        if(adopter == null) return null;
        String getAdopterQuery = "SELECT * FROM adoption_application WHERE adopter_id = "+ id + ";";
        List<AdoptionApplication> list = new LinkedList<>();
        try{
            ResultSet resultSet = conn.prepareStatement(getAdopterQuery).executeQuery();
            while(resultSet.next()){
                AdoptionApplication application =  AdoptionApplication.builder()
                        .appId(resultSet.getInt("app_id"))
                        .status(ApplicationStatus.valueOf(resultSet.getString("status").toUpperCase()))
                        .petID(resultSet.getLong("pet_id"))
                        .adopterId(resultSet.getLong("adopter_id"))
                        .build();
                list.add(application);
            }
            return list;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    public String addApplication(long petId, long adopterId) {
        String addApplicationQuery =
                String.format("INSERT INTO ADOPTION_APPLICATION(pet_id, adopter_id) " +
                                "VALUES(%d, %d);"
                        , petId, adopterId);
        System.out.println("Add Application Query = " + addApplicationQuery);
        try{
            conn.prepareStatement(addApplicationQuery).execute();
            return "Application Submitted!!";
        }catch (SQLException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
