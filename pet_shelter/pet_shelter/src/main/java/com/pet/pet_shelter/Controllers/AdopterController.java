package com.pet.pet_shelter.Controllers;

import com.pet.pet_shelter.DTOs.Adopter;
import com.pet.pet_shelter.Services.AdopterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/adopter")
public class AdopterController {

    @Autowired
    AdopterService adopterService;

    @PostMapping("/signIn")
    public ResponseEntity<Adopter> signIn(@RequestBody Map<String, String> body){
        Adopter adopter = adopterService.signIn(body.get("email"), body.get("password"));
        if(adopter == null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok().body(adopter);
        }
    }

    @PostMapping("/signUp")
    public ResponseEntity<String> signUp(@RequestBody Adopter adopter){
        String response = adopterService.signUp(adopter);
        if(response.equals("Adopter Created!!")){
            return ResponseEntity.ok().body(response);
        }
        return ResponseEntity.status(409).body(response);
    }
    @GetMapping("/getByEmail/{email}")
    public ResponseEntity<Adopter> getByEmail(@PathVariable String email){
        Adopter staff = adopterService.getByEmail(email);
        if(staff != null){
            return ResponseEntity.ok().body(staff);
        }
        return ResponseEntity.notFound().build();
    }
}
