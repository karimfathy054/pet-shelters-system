package com.pet.pet_shelter.DAOs.DTOs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;

@Builder
@Getter
@Setter
public class Adopter {
    long adopterId;
    String email;
    String password;
    String firstName;
    String secondName;
    String address;
    String phone;
    Timestamp joinDate;
}