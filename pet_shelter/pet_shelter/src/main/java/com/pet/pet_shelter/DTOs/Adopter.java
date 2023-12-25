package com.pet.pet_shelter.DTOs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Adopter {
    int id;
    String firstName;
    String lastName;
    String address;
    String phone;
}
