package com.pet.pet_shelter.Services;



import com.pet.pet_shelter.DAOs.AdoptionApplicationDao;
import com.pet.pet_shelter.DAOs.NotificationDao;
import com.pet.pet_shelter.DTOs.Adopter;
import com.pet.pet_shelter.DTOs.AdoptionApplication;
import com.pet.pet_shelter.DTOs.Notification;
import com.pet.pet_shelter.DTOs.Pet;
import com.pet.pet_shelter.ENUMS.ApplicationStatus;
import com.pet.pet_shelter.ENUMS.Gender;
import com.pet.pet_shelter.ENUMS.HouseTraining;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class AdopterService {
    private Connection conn;
    @Autowired
    NotificationDao notificationDao;
    @Autowired
    AdoptionApplicationDao adoptionApplicationDao;
    private String username = "root";
    private String password = "#mora951753132547698#";
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
                        .adopterId(resultSet.getLong("adopter_id"))
                        .phone(resultSet.getString("phone"))
                        .address(resultSet.getString("address"))
                        .firstName(resultSet.getString("firstName"))
                        .secondName(resultSet.getString("secondName"))
                        .joinDate(resultSet.getTimestamp("join_date"))
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
                        .joinDate(resultSet.getTimestamp("join_date"))
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
                        .joinDate(resultSet.getTimestamp("join_date"))
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
                        .status(ApplicationStatus.valueOf(resultSet.getString("status")))
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
        if(!adoptionApplicationDao.checkExistance(petId, adopterId)) return "you already have sent an application";
        String addApplicationQuery ="INSERT INTO ADOPTION_APPLICATION(pet_id, adopter_id) " + "VALUES("+petId+", "+adopterId+");";
        System.out.println("Add Application Query = " + addApplicationQuery);
        try{
            conn.prepareStatement(addApplicationQuery).execute();
            return "Application Submitted!!";
        }catch (SQLException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
    
    private Map<String, String > mapToDB = new HashMap<>(){{
        put("birthDate", "date_of_birth");
        put("healthStatus", "health_status");
        put("houseTraining", "house_training");
        put("neuteringStatus", "neutering_status");
        put("shelterId", "shelter_id");
        put("joinDate", "join_date");
        put("id", "idpet");
    }};
    public List<Pet> searchPets(String field, String key, String order) {
        if(mapToDB.containsKey(field)) field = mapToDB.get(field);
        if(mapToDB.containsKey(order)) order = mapToDB.get(order);
        String searchQuery = String.format(
                "SELECT * FROM pet WHERE %s LIKE '%s%%' ORDER BY %s"
                ,field, key, order
        );
        System.out.println("searchQuery = " + searchQuery);

        try{
            ResultSet resultSet = conn.prepareStatement(searchQuery).executeQuery();
            List<Pet> list = new LinkedList<>();
            while(resultSet.next()){
                list.add(
                        Pet.builder()
                                .id(resultSet.getLong("idpet"))
                                .name(resultSet.getString("name"))
                                .species(resultSet.getString("species"))
                                .breed(resultSet.getString("breed"))
                                .birthDate(resultSet.getDate("date_of_birth"))
                                .gender(Gender.valueOf(resultSet.getString("gender")))
                                .healthStatus(resultSet.getString("health_status"))
                                .behavior(resultSet.getString("behavior"))
                                .description(resultSet.getString("description"))
                                .houseTraining(HouseTraining.valueOf(resultSet.getString("house_training")))
                                .neuteringStatus(resultSet.getBoolean("neutering_status"))
                                .shelterId(resultSet.getLong("shelter_id"))
                                .joinDate(resultSet.getTimestamp("join_date"))
                                .build()
                );
            }
            return list;
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<Notification> getAllNotification(Long id) {

        return  notificationDao.getNotificationsforUser(id);
    }

    public String deleteStaff(Long id) {
        String addApplicationQuery = "DELETE FROM app_notify WHERE app_id = "+id+";";
        try{
            conn.prepareStatement(addApplicationQuery).execute();
            return "Notification Deleted!!";
        }catch (SQLException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
