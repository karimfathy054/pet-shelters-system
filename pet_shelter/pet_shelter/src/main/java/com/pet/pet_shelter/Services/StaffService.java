package com.pet.pet_shelter.Services;

import com.pet.pet_shelter.DTOs.Shelter;
import com.pet.pet_shelter.DTOs.Staff;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
@Service
public class StaffService {

    private Connection conn;
    private String username = "root";
    private String password = "password";
    private String url = "jdbc:mysql://localhost:3306/mydb";
    StaffService() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(url, username, password);
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
    private Staff getStaffByEmail(String email){
        String getUserQuery = "SELECT * FROM staff WHERE email = '"+ email + "';";
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
    private Shelter getShelterById(int id){
        String getUserQuery = "SELECT * FROM shelter WHERE id = '"+ id + "';";
        try{
            ResultSet resultSet = conn.prepareStatement(getUserQuery).executeQuery();
            if(resultSet.next()){
                return Shelter.builder()
                        .id(resultSet.getInt("idshelter"))
                        .location(resultSet.getString("location"))
                        .name(resultSet.getString("name"))
                        .phoneNumber(resultSet.getString("phone"))
                        .manager_id(Integer.parseInt(resultSet.getString("manager_id")))
                        .build();
            }
            return null;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    public Staff signIn(String email, String password){
        return getStaff(email, password);
    }
    public String createStaff(Staff staff){
        //System.out.println("staff.getShelterID() = " + staff.getShelterID());
        String shelterId;
        if(staff.getShelterID()==0){
            shelterId = "null";
        }else{
            shelterId = Integer.toString(staff.getShelterID());
        }
        String addStaff = String.format(
                "INSERT INTO " +
                        "staff(first_name, last_name, is_admin, phone, email, password, shelter_id) " +
                        "WHERE"
                , staff.getFirstName(), staff.getLastName(), staff.isIdAdmin()
                , staff.getPhone(), staff.getEmail(), staff.getPassword(), shelterId
        );
        //System.out.println("addStaff = " + addStaff);
        try{
            conn.prepareStatement(addStaff).execute();
            return "Staff Added!!";
        }catch (SQLException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
    public String makeManager(String email) {
        String makeStaffManager="";
        Staff staff = getStaffByEmail(email);
        if(staff != null){
            makeStaffManager = String.format(
                    "UPDATE staff " +
                            "SET is_admin = 1 " +
                            "WHERE email = %s;"
                    , email);
        }else{
            return "Staff not found";
        }
        try{
            conn.prepareStatement(makeStaffManager).execute();
            return "Role Changed";
        }catch (SQLException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
    public String deleteStaff(String email){
        String deleteStaff="";
        Staff staff = getStaffByEmail(email);
        if(staff != null){
            deleteStaff = String.format(
                    "DELETE FROM staff " +
                            "WHERE email = %s;"
                    , email);
        }else{
            return "Staff not found";
        }
        try{
            conn.prepareStatement(deleteStaff).execute();
            return "Staff Deleted";
        }catch (SQLException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
    public String associateStaffToShelter(String email, int shelterId) {
        String associate = "";
        Staff staff = getStaffByEmail(email);
        Shelter shelter = getShelterById(shelterId);
        if(staff != null){
            if(shelter != null){
                associate = String.format(
                        "UPDATE staff " +
                                "SET shelter_id = %s " +
                                "WHERE email = %s;"
                        ,shelter.getId() ,email);
            }else{
                return "Shelter not found";
            }
        }else{
            return "Staff not found";
        }
        try{
            conn.prepareStatement(associate).execute();
            return "Associate Staff to the Shelter successful.";
        }catch (SQLException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
