package com.pet.pet_shelter.DTOs;

import com.pet.pet_shelter.ENUMS.DocType;
import lombok.Builder;

@Builder

public class Document {
    String path;
    DocType type;
    int petID;
}
