package com.pet.pet_shelter.Services;

import com.pet.pet_shelter.DTOs.*;
import com.pet.pet_shelter.ENUMS.ApplicationStatus;
import org.springframework.stereotype.Service;

import java.sql.*;

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
                        .shelterId(resultSet.getInt("shelter_id"))
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
                        .shelterId(resultSet.getInt("shelter_id"))
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
                        .shelterId(resultSet.getInt("idshelter"))
                        .location(resultSet.getString("location"))
                        .name(resultSet.getString("name"))
                        .phoneNumber(resultSet.getString("phone"))
                        .managerId(Integer.parseInt(resultSet.getString("manager_id")))
                        .build();
            }
            return null;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
//    private AdoptionApplication getApplicationById(Long id){
//        String getUserQuery = "SELECT * FROM adoption_application WHERE id = '"+ id + "';";
//        try{
//            ResultSet resultSet = conn.prepareStatement(getUserQuery).executeQuery();
//            if(resultSet.next()){
//                return AdoptionApplication.builder()
//                        .adopterId(resultSet.getInt("")
//                        .build();
//            }
//            return null;
//        }catch (SQLException e){
//            e.printStackTrace();
//            return null;
//        }
//    }
    public Staff signIn(String email, String password){
        return getStaff(email, password);
    }
    public String createStaff(Staff staff){
        String shelterId;
        if(staff.getShelterId()==0){
            shelterId = "null";
        }else{
            shelterId = Integer.toString(staff.getShelterId());
        }
        String addStaff = String.format(
                "INSERT INTO " +
                        "staff(first_name, last_name, is_admin, phone, email, password, shelter_id) " +
                        "VALUES('%s', '%s', %b, '%s', '%s', '%s',%s);"
                , staff.getFirstName(), staff.getLastName(), staff.isIdAdmin()
                , staff.getPhone(), staff.getEmail(), staff.getPassword(), shelterId
        );
        System.out.println("Add Staff QUERY: " + addStaff);
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
                            "WHERE email = '%s';"
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
                            "WHERE email = '%s';"
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
                        ,shelter.getShelterId() ,email);
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

    public String addPet(Pet pet) {
        String shelterId;
        if(pet.getShelterId()==0){
            shelterId = "null";
        }else{
            shelterId = Integer.toString(pet.getShelterId());
        }
        String addPetQuery =
                String.format("INSERT INTO PET(name, species, breed, date_of_birth, gender, health_status, behavior, " +
                        "description, house_training, neutering_status, shelter_id, join_date)" +
                        "VALUES('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', %b, %s, '%s')"
                , pet.getName(), pet.getSpecies(), pet.getBreed(), pet.getBirthDate().toString(), pet.getGender(), pet.getHealthStatus()
                ,pet.getBehavior(), pet.getDescription(), pet.getTrainingStatus(), pet.isNeuteringStatus(), shelterId, new Date(System.currentTimeMillis()).toString());
        System.out.println("Add Pet Query = " + addPetQuery);
        try{
            conn.prepareStatement(addPetQuery).execute();
            return "Pet Added!!";
        }catch (SQLException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public String addAdopter(Adopter adopter) {
        String addAdopterQuery =
                String.format("INSERT INTO ADOPTER(email, password, firstName, secondName, address, phone) " +
                                "VALUES('%s', '%s', '%s', '%s', '%s', '%s');"
                        , adopter.getEmail(), adopter.getPassword(), adopter.getFirstName(), adopter.getSecondName(), adopter.getAddress(), adopter.getPhone());
        System.out.println("Add Adopter Query = " + addAdopterQuery);
        try{
            conn.prepareStatement(addAdopterQuery).execute();
            return "Adopter Added!!";
        }catch (SQLException e) {
            e.printStackTrace();
            return e.getMessage();
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

    public Staff getByEmail(String email) {
        String getStaffQuery = "SELECT * FROM staff WHERE email = '"+ email + "';";
        try{
            ResultSet resultSet = conn.prepareStatement(getStaffQuery).executeQuery();
            if(resultSet.next()){
                return Staff.builder()
                        .staffId(resultSet.getInt("staff_id"))
                        .firstName(resultSet.getString("first_name"))
                        .lastName(resultSet.getString("last_name"))
                        .shelterId(resultSet.getInt("shelter_id"))
                        .idAdmin((resultSet.getBoolean("is_admin")))
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


//    public String acceptApplication(boolean status, Long id) {
//        AdoptionApplication adoptionApplication = getApplicationById(id);
//
//         return "";
//    }
}
