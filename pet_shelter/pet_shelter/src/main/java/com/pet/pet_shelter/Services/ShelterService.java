package com.pet.pet_shelter.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pet.pet_shelter.DAOs.ShelterDao;
import com.pet.pet_shelter.DAOs.StaffDao;
import com.pet.pet_shelter.DTOs.Shelter;

@Service
public class ShelterService {
    @Autowired
    ShelterDao shelterDao;

    @Autowired
    StaffDao staffDao;

    public boolean addShelter(Shelter shelter){
        if(shelter.getName() == null || shelter.getName() .equals("")) return false;
        if(shelter.getLocation() == null || shelter.getLocation() .equals("")) return false;
        if(shelter.getPhoneNumber() == null || shelter.getPhoneNumber() .equals("")) return false;
        if(shelter.getManagerId() == null) return false;
        if(!staffDao.checkStaffIsAdmin(shelter.getManagerId())) return false;
        shelterDao.addShelter(shelter);
        return true;
    }

    public Shelter getShelterData(Integer shelterId){
        Optional<Shelter> res = shelterDao.getShelterById(shelterId);
        if(res.isPresent()) return res.get();
        else return null;
    }

    public Shelter getShelterByManagerId(Integer managerId){
        Optional<Shelter> res = shelterDao.getShelterByManagerId(managerId);
        if(res.isPresent()) return res.get();
        else return null;
    }
    public Shelter getShelterByManagerEmail(String managerEmail){
        Optional<Shelter> res = shelterDao.getShelterByManagerEmail(managerEmail);
        if(res.isPresent()) return res.get();
        else return null;
    }

    // public boolean deleteShelter(Integer shelterId,Integer staffId ){
    //     if(!staffDao.checkStaffIsAdmin(staffId)) return false;
    //     if(shelterDao.deleteShelter(shelterId) == 1) return true;
    //     return false;
    // }

    //change shelter management

    public boolean deleteShelter(Integer shelterId){
        return (shelterDao.deleteShelter(shelterId) == 1);
    }

    public boolean changeSheltermanager(String managerEmail , String shelterName){
        return (shelterDao.changeManager(managerEmail, shelterName) == 1);
    }

    

}
