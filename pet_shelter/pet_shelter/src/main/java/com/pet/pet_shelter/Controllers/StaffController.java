package com.pet.pet_shelter.Controllers;
import com.pet.pet_shelter.DAOs.DTOs.Adopter;
import com.pet.pet_shelter.DAOs.DTOs.Pet;
import com.pet.pet_shelter.DAOs.DTOs.Staff;
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
    StaffService staffService;
    @PostMapping("/login")
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
    @GetMapping("/getByEmail/{email}")
    public ResponseEntity<Staff> getByEmail(@PathVariable String email){
        Staff staff = staffService.getByEmail(email);
        if(staff != null){
            return ResponseEntity.ok().body(staff);
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping("/makeAdmin")
    public ResponseEntity<String> makeAdmin(@RequestBody Map<String,String> body){
            String response = staffService.makeAdmin(body.get("email"));
        if(response.equals("Role Changed"))  return ResponseEntity.ok().body(response);
        else return ResponseEntity.status(409).body(response);
    }
    @DeleteMapping("/deleteStaff")
    public ResponseEntity<String> deleteStaff(@RequestBody Map<String,String> body){
        String response = staffService.deleteStaff(body.get("email"));
        if(response.equals("Staff Deleted"))  return ResponseEntity.ok().body(response);
        else return ResponseEntity.status(404).body(response);
    }
    @PostMapping("/associateStaffToShelter")
    public ResponseEntity<String> associateStaffToShelter(@RequestBody Map<String,String> body){
        String response = staffService.associateStaffToShelter(body.get("email"), (body.get("shelterName")));
        if(response.equals("Staff become in the shelter"))  return ResponseEntity.ok().body(response);
        else return ResponseEntity.status(409).body(response);
    }

    @PostMapping("/addPet")
    public ResponseEntity<String> addPet(@RequestBody Pet pet){
        String response = staffService.addPet(pet);
        if(response.equals("Pet Added!!")){
            return ResponseEntity.ok().body(response);
        }
        return ResponseEntity.status(409).body(response);
    }
    @PostMapping("/addAdopter")
    public ResponseEntity<String> addAdopter(@RequestBody Adopter adopter){
        String response = staffService.addAdopter(adopter);
        if(response.equals("Adopter Added!!")){
            return ResponseEntity.ok().body(response);
        }
        return ResponseEntity.status(409).body(response);
    }


    @PostMapping("/acceptApplication")
    public ResponseEntity<String> acceptApplication(@RequestBody Map<String, String> body){
        String status = (body.get("status"));
        Long id = Long.valueOf(body.get("id"));
        String response = staffService.acceptApplication(status,id);
        if(response.equals("Accept and Record Added!!")||response.equals("Rejected!!")){
            return ResponseEntity.ok().body(response);
        }
        return ResponseEntity.status(409).body(response);
    }
}
