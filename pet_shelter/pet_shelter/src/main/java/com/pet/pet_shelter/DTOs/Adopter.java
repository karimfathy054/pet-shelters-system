package com.pet.pet_shelter.DTOs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Builder
@Getter
@Setter
public class Adopter {
    int adopterId;
    String email;
    String password;
    String firstName;
    String lastName;
    String address;
    String phone;
    Date joinDate;
}
