package com.pet.pet_shelter.Services;


import com.pet.pet_shelter.DAOs.AdopterDao;
import com.pet.pet_shelter.DAOs.AdoptionApplicationDao;
import com.pet.pet_shelter.DAOs.NotificationDao;
import com.pet.pet_shelter.DTOs.*;
import com.pet.pet_shelter.ENUMS.ApplicationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.List;

@Service
public class StaffService {

    private Connection conn;
    @Autowired
    AdopterDao adopterDao;
    @Autowired
    AdoptionApplicationDao adoptionApplicationDao;
    @Autowired
    NotificationDao notificationDao;
    private String username = "scott";
    private String password = "01223624409ABab@";
    private String url = "jdbc:mysql://localhost:3306/mydb";
    StaffService() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(url, username, password);
    }
    private Staff getStaff(String email, String password){
        String getUserQuery = "SELECT * FROM staff WHERE email = '"+ email +
                "' AND password = '" + password + "';";
        try{
            ResultSet resultSet = conn.prepareStatement(getUserQuery).executeQuery();
            if(resultSet.next()){
                return Staff.builder()
                        .id(resultSet.getInt("staff_id"))
                        .firstname(resultSet.getString("first_name"))
                        .lastName(resultSet.getString("last_name"))
                        .shelterID(resultSet.getInt("shelter_id"))
                        .isAdmin(resultSet.getBoolean("is_admin"))
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
                        .id(resultSet.getInt("staff_id"))
                        .firstname(resultSet.getString("first_name"))
                        .lastName(resultSet.getString("last_name"))
                        .shelterID(resultSet.getInt("shelter_id"))
                        .isAdmin(resultSet.getBoolean("is_admin"))
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
    private Shelter getShelterByName(String shelterName) {
        String getUserQuery = "SELECT * FROM shelter WHERE name = '"+ shelterName + "';";
        try{
            ResultSet resultSet = conn.prepareStatement(getUserQuery).executeQuery();
            if(resultSet.next()){
                return Shelter.builder()
                        .id(resultSet.getInt("idshelter"))
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
                        .managerId(Integer.parseInt(resultSet.getString("manager_id")))
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
        String shelterId;
        if(staff.getShelterID()==0){
            shelterId = "null";
        }else{
            shelterId = Integer.toString(staff.getShelterID());
        }
        String addStaff = String.format(
                "INSERT INTO " +
                        "staff(first_name, last_name, is_admin, phone, email, password, shelter_id) " +
                        "VALUES('%s', '%s', %b, '%s', '%s', '%s',%s);"
                , staff.getFirstname(), staff.getLastName(), staff.getIsAdmin()
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
    public String makeAdmin(String email) {
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
    public String associateStaffToShelter(String email, String ShelterName) {
        String associate = "";
        Staff staff = getStaffByEmail(email);
        Shelter shelter = getShelterByName(ShelterName);
        if(staff != null){
            if(shelter != null){
                associate = String.format(
                        "UPDATE staff " +
                                "SET shelter_id = %s " +
                                "WHERE email = '%s';"
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



    public String addPet(Pet pet) {
        String shelterId;
        if(pet.getShelterId()==0){
            shelterId = "null";
        }else{
            shelterId = Long.toString(pet.getShelterId());
        }
        String addPetQuery =
                String.format("INSERT INTO PET(name, species, breed, date_of_birth, gender, health_status, behavior, " +
                        "description, house_training, neutering_status, shelter_id)" +
                        "VALUES('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', %b, %s)"
                , pet.getName(), pet.getSpecies(), pet.getBreed(), pet.getBirthDate().toString(), pet.getGender(), pet.getHealthStatus()
                ,pet.getBehavior(), pet.getDescription(), pet.getHouseTraining(), pet.getNeuteringStatus(), shelterId);
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
                        .id(resultSet.getInt("staff_id"))
                        .firstname(resultSet.getString("first_name"))
                        .lastName(resultSet.getString("last_name"))
                        .shelterID(resultSet.getInt("shelter_id"))
                        .isAdmin((resultSet.getBoolean("is_admin")))
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


    private AdoptionRecord getRecordById(Long id){
        String getUserQuery = "SELECT * FROM adoption_record WHERE pet_id = '"+ id + "';";
        try{
            ResultSet resultSet = conn.prepareStatement(getUserQuery).executeQuery();
            if(resultSet.next()){
                return AdoptionRecord.builder()
                        .adoptingFamily(resultSet.getString("adopting_family"))
                        .petId(resultSet.getLong("pet_id"))
                        .build();
            }
            return null;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    private AdoptionApplication getApplicationById(Long id){
        String getUserQuery = "SELECT * FROM adoption_application WHERE app_id = '"+ id + "';";
        try{
            ResultSet resultSet = conn.prepareStatement(getUserQuery).executeQuery();
            if(resultSet.next()){
                return AdoptionApplication.builder()
                        .adopterId(resultSet.getInt("adopter_id"))
                        .status(ApplicationStatus.valueOf(resultSet.getString("status")))
                        .petID(resultSet.getInt("pet_id"))
                        .appId(resultSet.getInt("app_id"))
                        .build();
            }
            return null;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    // procedure
    public void rejectAnotherApplication(Long petID){
        List<AdoptionApplication> list = adoptionApplicationDao.getApplicationsByPetId(petID);
        for(int i=0; i< list.size(); i++){
            if(list.get(i).getStatus().equals(ApplicationStatus.pending)){
            Adopter adopter = adopterDao.getAdopterByID(list.get(i).getAdopterId()).get();
            String updateStatus = String.format(
                    "UPDATE adoption_application " +
                            "SET status = '%s' " +
                            "WHERE app_id = %s;"
                    , ApplicationStatus.rejected,list.get(i).getAppId());
            try{
                conn.prepareStatement(updateStatus).execute();
                Notification notification = Notification.builder()
                        .adopterId(adopter.getAdopterId())
                        .appId(list.get(i).getAppId())
                        .notificationTime(new Timestamp(System.currentTimeMillis()))
                        .build();
                // procedure
                notificationDao.addNotification(notification);
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        }
    }
    public String acceptApplication(String status, Long id) {
        AdoptionApplication adoptionApplication = getApplicationById(id);
        if(adoptionApplication == null){
            return "app not found";
        }else if (status.equals("accept") ){
            Adopter adopter = adopterDao.getAdopterByID(adoptionApplication.getAdopterId()).get();
            String fullName = adopter.getFirstName()+" "+adopter.getSecondName();
            AdoptionRecord adoptionRecord = getRecordById(adoptionApplication.getPetID());
            if(adoptionRecord != null) return "pet already taken ";
            else{
                String addRecordQuery =
                        String.format("INSERT INTO adoption_record(adopting_family, pet_id) " +
                                        "VALUES('%s', %s);"
                                ,fullName,adoptionApplication.getAdopterId());
                try{
                    conn.prepareStatement(addRecordQuery).execute();
                    // procedures
                    String updateStatus = String.format(
                            "UPDATE adoption_application " +
                                    "SET status = '%s' " +
                                    "WHERE app_id = %s;"
                            , ApplicationStatus.approved,adoptionApplication.getAppId());
                    try{
                        conn.prepareStatement(updateStatus).execute();
                        // procedure....
                        rejectAnotherApplication(adoptionApplication.getPetID());
                        Notification notification = Notification.builder()
                                .adopterId(adopter.getAdopterId())
                                .appId(adoptionApplication.getAppId())
                                // procedure...
                                .notificationTime(new Timestamp(System.currentTimeMillis()))
                                .build();
                        notificationDao.addNotification(notification);
                        return "Accept and Record Added!!";
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                        return e.getMessage();
                    }
                }catch (SQLException e) {
                    e.printStackTrace();
                    return e.getMessage();
                }

            }
        }else{
            String updateStatus = String.format(
                    "UPDATE adoption_application " +
                            "SET status = 'rejected' " +
                            "WHERE app_id = %s;"
                    , adoptionApplication.getAppId());
            try{
                conn.prepareStatement(updateStatus).execute();
                Adopter adopter = adopterDao.getAdopterByID(adoptionApplication.getAdopterId()).get();
                // procedure
                Notification notification = Notification.builder()
                        .adopterId(adopter.getAdopterId())
                        .appId(adoptionApplication.getAppId())
                        .notificationTime(new Timestamp(System.currentTimeMillis()))
                        .build();
                notificationDao.addNotification(notification);
                return "Rejected!!";
            }
            catch (SQLException e) {
                e.printStackTrace();
                return e.getMessage();
            }
        }
    }
}
