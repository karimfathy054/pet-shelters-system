package com.pet.pet_shelter.Services;


import com.pet.pet_shelter.DTOs.Staff;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class RequestService {

    private Connection conn;
    private String username = "root";
    private String password = "password";
    private String url = "jdbc:mysql://localhost:3306/mydb";
    RequestService() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(url, username, password);
    }
    public String firstSelect(){

        try{
            String sql = "select * from users";
            ResultSet rs2 = conn.prepareStatement(sql).executeQuery();
            if(rs2.next()){
                return rs2.getString("email");
            }
            return "Not found!!";
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    private Staff getStaff(String email, String password){
        String getUserQuery = "SELECT * FROM staff WHERE email = '"+ email +
                "' AND password = '" + password + "';";
        try{
            ResultSet resultSet = conn.prepareStatement(getUserQuery).executeQuery();
            if(resultSet.next()){
                return Staff.builder()
                        .staffId(resultSet.getInt("staff_id"))
                        .firstName(resultSet.getString("first_name"))
                        .lastName(resultSet.getString("last_name"))
                        .shelterID(resultSet.getInt("shelter_id"))
                        .idAdmin(resultSet.getBoolean("is_admin"))
                        .phone(resultSet.getString("phone"))
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

    public String getHello(){
        return "Hello World!!";
    }


    public Staff signIn(String email, String password){
        return getStaff(email, password);
    }
    public String createStaff(Staff staff){
        System.out.println("staff.getShelterID() = " + staff.getShelterID());
        String shelterId;
        if(staff.getShelterID()==0){
            shelterId = "null";
        }else{
            shelterId = Integer.toString(staff.getShelterID());
        }
        String addStaff = String.format(
                "INSERT INTO " +
                        "staff(first_name, last_name, is_admin, phone, email, password, shelter_id) " +
                        "VALUES(\"%s\", \"%s\", %b, \"%s\", \"%s\", \"%s\", %s);"
                , staff.getFirstName(), staff.getLastName(), staff.isIdAdmin()
                , staff.getPhone(), staff.getEmail(), staff.getPassword(), shelterId
        );
        System.out.println("addStaff = " + addStaff);
        try{
            conn.prepareStatement(addStaff).execute();
            return "Staff Added!!";
        }catch (SQLException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

}
