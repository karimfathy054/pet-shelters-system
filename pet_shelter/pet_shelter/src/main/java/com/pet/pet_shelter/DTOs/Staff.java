package com.pet.pet_shelter.DTOs;

import com.pet.pet_shelter.ENUMS.Role;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Staff {
    int staffId;
    String firstName;
    String lastName;
    boolean idAdmin;
    int shelterID;
    String phone;
    String email;
    String password;
}
