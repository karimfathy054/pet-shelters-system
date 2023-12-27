package com.pet.pet_shelter.Service;
import com.pet.pet_shelter.DTOs.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AppService {
    public String login(Map<String, String> body) {
        return "";
    }

    public String signup(Map<String, String> body) {
        return "";
    }

    public boolean createShelter(Shelter shelter) {
        return true;
    }

    public boolean deleteShelter(Long id) {
        return true;
    }

    public boolean changeAttribute(Shelter shelter) {
        return  true;
    }

    public List<Shelter> getByName(String name) {
        return null;
    }

    public List<Shelter> getById(Long id) {
        return null;
    }

    public List<Shelter> getByLocation(String location) {
        return null;
    }

    public List<Shelter> getAllShelters() {
        return null;
    }

    public boolean createStaffProfile(Staff staff) {
        return true;
    }

    public boolean changeStaffRole(Long id, String role) {
        return true;
    }

    public boolean deleteStaff(Long id) {
        return true;
    }

    public boolean staffAssociatedWithShelter(Long staff, Long shelter) {
        return true;
    }

    public boolean addPetProfile(Pet pet) {
        return true;
    }

    public boolean removePet(Long id) {
        return true;
    }

    public Pet getPetById(Long id) {
        return null;
    }

    public List<Pet> getPetByName(String name) {
        return null;
    }

    public List<Pet> getPetBySpecies(String species) {
        return null;
    }

    public List<Pet> getPetBybreed(String breed) {
        return null;
    }

    public List<Pet> getPetByBirthDate(Date birthDate) {
        return null;
    }

    public List<Pet> getPetByGender(String gender) {
        return null;
    }

    public List<Pet> getPetByShelterID(Long id) {
        return null;
    }

    public boolean changeProfilePet(Map<String, String> body) {
        return false;
    }

    public List<Shelter> getAllStaff() {
        return null;
    }

    public List<Shelter> getAllPets() {
        return null;
    }

    public boolean AddAdoptionApplication(AdoptionApplication adoptionApplication) {
        return true;
    }

    public List<AdoptionApplication> getAllAdoptionApplication() {
        return null;
    }

    public boolean acceptAdoptionApplication(Long id) {
        return true;
    }

    public boolean rejectAdoptionApplication(Long id) {
        return true;
    }

    public boolean createApplicationRecord(Long idPet, Long idAdopt) {
        return true;
    }

    public boolean createDocument(Document document) {
        return true;
    }

    public List<Document> getAllDocuments() {
        return null;
    }

    public boolean removeDocument(String path) {
        return true;
    }
}
