package com.pet.pet_shelter.DAOs.DTOs;

import com.pet.pet_shelter.ENUMS.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Staff {
    int id;
    String firstname;
    String lastName;
    int shelterID;
    Boolean isAdmin;
    String phone;
    String email;
    String password;
}
