package com.pet.pet_shelter.Controllers;
import com.pet.pet_shelter.DTOs.Staff;
import com.pet.pet_shelter.Services.RequestService;
import com.pet.pet_shelter.Services.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestController
@CrossOrigin
@RequestMapping("/staff")
public class StaffController {
    @Autowired
    RequestService requestService;
    @Autowired
    StaffService staffService;
    @GetMapping("/login")
    public ResponseEntity<Staff> login(@RequestBody Map<String, String> body){
        Staff staff = staffService.signIn(body.get("email"), body.get("password"));
        if(staff == null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok().body(staff);
        }
    }
    @PostMapping("/createStaff")
    public ResponseEntity<String> createStaff(@RequestBody Staff staff){
        String response = staffService.createStaff(staff);
        if(response.equals("Staff Added!!")){
            return ResponseEntity.ok().body(response);
        }
        return ResponseEntity.status(409).body(response);
    }
    @PostMapping("/makeManager")
    public ResponseEntity<String> makeManager(@RequestBody Map<String,String> body){
            String response = staffService.makeManager(body.get("email"));
        if(response.equals("Role Changed"))  return ResponseEntity.ok().body(response);
        else return ResponseEntity.status(409).body(response);
    }
    @PostMapping("/deleteStaff")
    public ResponseEntity<String> deleteStaff(@RequestBody Map<String,String> body){
        String response = staffService.deleteStaff(body.get("email"));
        if(response.equals("Staff Deleted"))  return ResponseEntity.ok().body(response);
        else return ResponseEntity.status(404).body(response);
    }
    @PostMapping("/associateStaffToShelter")
    public ResponseEntity<String> associateStaffToShelter(@RequestBody Map<String,String> body){
        String response = staffService.associateStaffToShelter(body.get("email"), Integer.parseInt(body.get("shelterId")));
        if(response.equals("Staff become in the shelter"))  return ResponseEntity.ok().body(response);
        else return ResponseEntity.status(409).body(response);
    }
}
