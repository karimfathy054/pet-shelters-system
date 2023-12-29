package com.pet.pet_shelter.DTOs;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Adopter {
    Long id;
    String username;
    String email;
    String password;
    Timestamp createTime;
    
}
