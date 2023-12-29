package com.pet.pet_shelter.DTOs;


import java.sql.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Notification {
    Long adopterId;
    Long appId;
    Date date;
}
