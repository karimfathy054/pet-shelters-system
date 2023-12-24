package com.pet.pet_shelter.DTOs;

import lombok.Builder;

@Builder
public class Adopter {
    int id;
    String firstName;
    String lastName;
    String address;
    String phone;
}
