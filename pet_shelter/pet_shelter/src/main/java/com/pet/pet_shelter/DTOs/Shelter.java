package com.pet.pet_shelter.DTOs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Shelter {
    int shelterId;
    String name;
    String location;
    String phoneNumber;
    int managerId;
}
