package com.pet.pet_shelter.DTOs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Shelter {
    int id;
    String name;
    String location;
    String phoneNumber;
}
