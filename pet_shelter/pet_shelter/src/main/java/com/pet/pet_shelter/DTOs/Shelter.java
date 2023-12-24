package com.pet.pet_shelter.DTOs;

import lombok.Builder;

@Builder
public class Shelter {
    int id;
    String name;
    String location;
    String phoneNumber;
}
