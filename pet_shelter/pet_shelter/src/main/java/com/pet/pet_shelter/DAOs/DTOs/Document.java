package com.pet.pet_shelter.DAOs.DTOs;

import com.pet.pet_shelter.ENUMS.DocType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Document {
    String path;
    DocType type;
    Long petID;
}
