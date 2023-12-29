package com.pet.pet_shelter.DTOs;

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
    int shelterId;
    String phone;
    String email;
    String password;
}
