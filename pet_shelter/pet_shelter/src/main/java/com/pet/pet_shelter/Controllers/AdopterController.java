package com.pet.pet_shelter.Controllers;


import com.pet.pet_shelter.DTOs.Adopter;
import com.pet.pet_shelter.DTOs.AdoptionApplication;
import com.pet.pet_shelter.DTOs.Notification;
import com.pet.pet_shelter.DTOs.Pet;
import com.pet.pet_shelter.Services.AdopterService;
import com.pet.pet_shelter.Services.PetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@CrossOrigin
@RequestMapping("/adopter")
public class AdopterController {

    @Autowired
    AdopterService adopterService;

    @Autowired
    PetService petService;

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

    @GetMapping("/getApplications/{id}")
    public ResponseEntity<List<AdoptionApplication>> getApplications(@PathVariable long id){
        List<AdoptionApplication> list = adopterService.getAllApplications(id);
        if(list == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping("/addApplication")
    public ResponseEntity<String> addApplication(@RequestBody Map<String, Long> body){
        String response = adopterService.addApplication(body.get("petID"), body.get("adopterId"));
        if(response.equals("Application Submitted!!")){
            return ResponseEntity.ok().body(response);
        }
        return ResponseEntity.status(409).body(response);
    }
    @GetMapping("/search/{field}/{key}/{order}")
    public ResponseEntity<List<Pet>> searchPets(@PathVariable String field, @PathVariable String key, @PathVariable String order){
        System.out.println("field = " + field);
        System.out.println("key = " + key);
        System.out.println("order = " + order);
        if(field.equals("location")){
            List<Pet> res = petService.getPetsByLocation(key);
            if(res.isEmpty()) return ResponseEntity.notFound().build();
            return ResponseEntity.ok().body(res);
        }
        else if(field.equals("shelterName")){
            List<Pet> res = petService.getPetsByShelterName(key,order);
            if(res.isEmpty()) return ResponseEntity.notFound().build();
            return ResponseEntity.ok().body(res);
        }
        List<Pet> list = adopterService.searchPets(field, key, order);
        if(list==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(list);
    }
    @GetMapping("/shelterName/{key}")
    public ResponseEntity<List<Pet>> getPetsByShelterId(@PathVariable int key){
        System.out.println("key = " + key);
        List<Pet> res = petService.getPetsByShelterId(key);
        if(res.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(res);
    }
    //----------------------------------------
    @GetMapping("/getNotification/{id}")
    public  ResponseEntity<List<Notification>> getNotification(@PathVariable Long id){
        List<Notification> list = adopterService.getAllNotification(id);
        if(list == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(list);
    }
    @DeleteMapping("/deleteNotification/{id}")
    public ResponseEntity<String> deleteNotification(@PathVariable Long id){
        String response = adopterService.deleteStaff(id);
        if(response.equals("Notification Deleted!!"))  return ResponseEntity.ok().body(response);
        else return ResponseEntity.status(404).body(response);
    }

    @GetMapping("/pets")
    public List<Pet> getAllPets() {
        return petService.getAll();
    }
    


}
