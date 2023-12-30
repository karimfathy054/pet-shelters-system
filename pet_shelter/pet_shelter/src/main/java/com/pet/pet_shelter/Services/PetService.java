package com.pet.pet_shelter.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pet.pet_shelter.DAOs.PetDao;
import com.pet.pet_shelter.DTOs.Pet;

@Service
public class PetService {
    @Autowired
    PetDao petDao;

    public List<Pet> getAll(){
        return petDao.findAll();
    }

    public List<Pet> getPetsByLocation(String location){
        return petDao.findPetByLocation(location);
    }

    public List<Pet> getPetsByShelterId(int shelterId){
        return petDao.findPetsByShelterId(shelterId);
    }
    
    public List<Pet> getPetsByShelterName(String shelterName,String order){
        return petDao.findPetByShelterName(shelterName,order);
    }

    public boolean removePet(long petId){
        return (petDao.removePet(petId) == 1);
    }


    
}
