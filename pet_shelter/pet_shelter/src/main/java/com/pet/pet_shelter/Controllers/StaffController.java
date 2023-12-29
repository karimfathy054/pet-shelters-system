package com.pet.pet_shelter.Controllers;

import com.pet.pet_shelter.DTOs.Staff;
import com.pet.pet_shelter.Services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/staff")
public class StaffController {
    @Autowired
    RequestService requestService;

    @GetMapping("/login")
    public ResponseEntity<Staff> login(@RequestBody Map<String, String> body){
        Staff staff = requestService.signIn(body.get("email"), body.get("password"));
        if(staff == null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok().body(staff);
        }
    }

    @PostMapping("/createStaff")
    public ResponseEntity<String> createStaff(@RequestBody Staff staff){
        String response = requestService.createStaff(staff);
        if(response.equals("Staff Added!!")){
            return ResponseEntity.ok().body(response);
        }
        return ResponseEntity.status(409).body(response);
    }
}
