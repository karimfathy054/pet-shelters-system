package com.pet.pet_shelter.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pet.pet_shelter.DTOs.Shelter;
import com.pet.pet_shelter.Services.ShelterService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/shelter")
public class ShelterController {
    @Autowired
    ShelterService shelterService;

    @GetMapping("/{shelterId}")
    public ResponseEntity<Shelter> getShelterById(@PathVariable int shelterId) {
        Shelter shelter = shelterService.getShelterData(shelterId);
        if(shelter == null) return ResponseEntity.status(409).body(null);
        else return ResponseEntity.ok().body(shelter);
    }

    @GetMapping("/managerId={managerId}")
    public ResponseEntity<Shelter> getShelterByMangerId(@PathVariable int managerId) {
        Shelter shelter = shelterService.getShelterByManagerId(managerId);
        if(shelter == null) return ResponseEntity.status(409).body(null);
        else return ResponseEntity.ok().body(shelter);
    }
    
    @GetMapping("/managerEmail={managerEmail}")
    public ResponseEntity<Shelter> getShelterByMangerEmail(@PathVariable String managerEmail) {
        Shelter shelter = shelterService.getShelterByManagerEmail(managerEmail);
        if(shelter == null) return ResponseEntity.status(409).body(null);
        else return ResponseEntity.ok().body(shelter);
    }

    @PostMapping("/add")
    public boolean addNewShelter(@RequestBody Shelter shelter) {
        return shelterService.addShelter(shelter);
    }
    
    @DeleteMapping("/{shelterId}")
    public boolean deleteShelter(@PathVariable int shelterID) {
        return shelterService.deleteShelter(shelterID);
    }
    
    @PutMapping("/shelterName={shelterName}&&newManegerEmail={managerEmail}")
    public boolean putMethodName(@PathVariable String shelterName, @PathVariable String managerEmail) {
        return shelterService.changeSheltermanager(managerEmail, shelterName);
    }
    
}
