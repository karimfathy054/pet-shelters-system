package com.pet.pet_shelter.Services;


import com.pet.pet_shelter.DTOs.Shelter;
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
//    public String firstSelect(){
//
//        try{
//            String sql = "select * from users";
//            ResultSet rs2 = conn.prepareStatement(sql).executeQuery();
//            if(rs2.next()){
//                return rs2.getString("email");
//            }
//            return "Not found!!";
//        }catch (SQLException e){
//            throw new RuntimeException(e);
//        }
//    }
//public String getHello(){
//    return "Hello World!!";
//}

}
