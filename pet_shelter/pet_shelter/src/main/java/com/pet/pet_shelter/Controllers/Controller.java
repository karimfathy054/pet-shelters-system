package com.pet.pet_shelter.Controllers;

import com.pet.pet_shelter.DTOs.*;
import com.pet.pet_shelter.Services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class Controller {

    @Autowired
    RequestService requestService;

    @GetMapping("/hello")
    public String hello (){
        return requestService.getHello();
    }

    @GetMapping("/getUser")
    public String getUser(){
        return requestService.firstSelect();
    }
//    @Autowired
//    AppService appService;
//    @PostMapping("/login")
//    public String login(@RequestBody Map<String, String> body){
//        return appService.login(body);
//    }
//    @PostMapping("/signup")
//    public String signup (@RequestBody Map<String, String> body){
//        return appService.signup(body);
//    }
//    @PostMapping("/createShelter")
//    public String createShelter (@RequestBody Shelter shelter){
//        boolean status = appService.createShelter(shelter);
//        if(status) return "Creation successful.";
//        else return "Creation failed.";
//    }
//    @DeleteMapping("/ShelterId={id}")
//    public String deleteShelter (@PathVariable Long id){
//        boolean status = appService.deleteShelter(id);
//        if(status) return "Deletion successful.";
//        else return "Deletion failed.";
//    }
//    @PostMapping("/changeAttribute")
//    public String changeAttribute(@RequestBody Shelter shelter){
//        boolean status = appService.changeAttribute(shelter);
//        if(status) return "The attribute has been changed.";
//        else return "The shelter not found.";
//    }
//    @GetMapping("/name={name}")
//    public List<Shelter> findByName(@PathVariable String name){
//        return appService.getByName(name);
//    }
//    @GetMapping("/id={id}")
//    public List<Shelter> findById(@PathVariable Long id){
//        return appService.getById(id);
//    }
//    @GetMapping("/location={location}")
//    public List<Shelter> findByLocation(@PathVariable String location){
//        return appService.getByLocation(location);
//    }
//    @GetMapping("/phone={phone}")
//    public List<Shelter> findByPhone(@PathVariable String phone){
//        return appService.getByLocation(phone);
//    }
//    @GetMapping("/allShelters")
//    public List<Shelter> getAll(){return  appService.getAllShelters();}
//    @PostMapping("/createStaffProfile")
//    public String createStaffProfile(@RequestBody Staff staff){
//        boolean status = appService.createStaffProfile(staff);
//        if(status) return "The profile has been created.";
//        else return "There is error.";
//    }
//    @PostMapping("/changeStaffRole")
//    public String changeStaffRole(@RequestBody Map<String,String> body){
//        boolean status = appService.changeStaffRole(Long.valueOf(body.get("id")),body.get("role"));
//        if(status) return "This profile is not found .";
//        else return "The role has been changed.";
//    }
//    @DeleteMapping("/staffId={id}")
//    public String deleteStaff (@PathVariable Long id){
//        boolean status = appService.deleteStaff(id);
//        if(status) return "Deletion successful.";
//        else return "Deletion failed.";
//    }
//    @GetMapping("/allStaff")
//    public List<Shelter> getAllStaff(){return  appService.getAllStaff();}
//    @PostMapping("/staff={staff}withShelter={shelter}")
//    public String staffAssociatedWithShelter(@PathVariable Long staff,@PathVariable Long shelter){
//        boolean status = appService.staffAssociatedWithShelter(staff,shelter);
//        if(status) return "Association successful.";
//        else return "Association failed.";
//    }
//    @PostMapping("/addPetProfile")
//    public String addPetProfile(@RequestBody Pet pet){
//        boolean status = appService.addPetProfile(pet);
//        if(status) return "Adding pet successful.";
//        else return "Adding pet failed.";
//    }
//    @DeleteMapping("/PetId={id}")
//    public String removePet(@PathVariable Long id){
//        if(appService.removePet(id)) return "pet is removed successfully";
//        return "Can not remove pet";
//    }
//    @GetMapping("/id={id}")
//    public Pet findPetByID(@PathVariable Long id){
//        return appService.getPetById(id);
//    }
//    @GetMapping("/name={name}")
//    public List<Pet> findPetByName(@PathVariable String name){
//        return appService.getPetByName(name);
//    }
//    @GetMapping("/species={species}")
//    public List<Pet> findPetBySpecies(@PathVariable String species){
//        return appService.getPetBySpecies(species);
//    }
//    @GetMapping("/birthDate={birthDate}")
//    public List<Pet> findPetByBirthDate(@PathVariable Date birthDate){
//        return appService.getPetByBirthDate(birthDate);
//    }
//    @GetMapping("/breed={breed}")
//    public List<Pet> findPetByBreed(@PathVariable String breed){
//        return appService.getPetBybreed(breed);
//    }
//    @GetMapping("/gender={gender}")
//    public List<Pet> findPetByGender(@PathVariable String gender){
//        return appService.getPetByGender(gender);
//    }
//    @GetMapping("/shelterID={id}")
//    public List<Pet> findPetByShelterID(@PathVariable Long id){
//        return appService.getPetByShelterID(id);
//    }
//    //Find by age //////////////////////////////////////////////////////////////////
//    //Implement filters for sorting and viewing pets based on attributes like house-training,
//    //vaccinations, or spaying/neutering status. This will return all pets and sort in front or sort in back and return package
//    @PostMapping("/changeProfilePet")
//    public String changeProfilePet(@RequestBody Map<String,String> body){
//        boolean status = appService.changeProfilePet(body);
//        if(status) return "Editing success.";
//        else return "Profile not found.";
//    }
//    @GetMapping("/allPets")
//    public List<Shelter> getAllPets(){return  appService.getAllPets();}
//    @PostMapping("AddAdoptionApplication")
//    public String AddAdoptionApplication(@RequestBody AdoptionApplication adoptionApplication){
//        boolean status = appService.AddAdoptionApplication(adoptionApplication);
//        if(status) return "Adding success.";
//        else return "Adding failed.";
//    }
//    @GetMapping("/getAllAdoptionApplication")
//    public List<AdoptionApplication> getAllAdoptionApplication(){return  appService.getAllAdoptionApplication();}
//    @PostMapping("/acceptAdoptionApplication={id}")
//    public String acceptAdoptionApplication(@PathVariable Long id){
//        boolean status = appService.acceptAdoptionApplication(id);
//        if(status) return "Acceptance is successful.";
//        else return "Acceptance is Failed.";
//    }
//    @PostMapping("/rejectAdoptionApplication={id}")
//    public String rejectAdoptionApplication(@PathVariable Long id){
//        boolean status = appService.rejectAdoptionApplication(id);
//        if(status) return "Rejection is successful.";
//        else return "Rejection is Failed.";
//    }
//    @PostMapping("/createApplicationRecordIdPet={idPet}WithIdAdopt={idAdopt]")
//    public String createApplicationRecord(Long idPet, Long idAdopt){
//        boolean status = appService.createApplicationRecord(idPet,idAdopt);
//        if(status) return "creation Application Record is successful.";
//        else return "creation Application Record is Failed.";
//    }
//    @PostMapping("/createDocument")
//    public String createDocument(@RequestBody Document document){
//        boolean status = appService.createDocument(document);
//        if(status) return "creation Document is successful.";
//        else return "creation Document is Failed.";
//    }
//    @GetMapping("/allDocuments")
//    public List<Document> getAllDocuments(){return  appService.getAllDocuments();}
//    @DeleteMapping("/documentPath={path}")
//    public String removeDocument(@PathVariable String path){
//        if(appService.removeDocument(path)) return "Document is removed successfully";
//        return "Can not remove Document";
//    }
}
