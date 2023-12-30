package com.pet.pet_shelter.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.pet.pet_shelter.DTOs.Document;
import com.pet.pet_shelter.Services.DocumentsService;


@RestController
@RequestMapping("/documents")
@CrossOrigin
public class DocumentController {
    @Autowired
    DocumentsService docService;

    @GetMapping("/id={petId}")
    public List<Document> getMethodName(@PathVariable long petId) {
        return docService.getPetsDocuments(petId);
    }

    @PostMapping("/add")
    public boolean addDocument(@RequestBody Document doc) {
        return docService.addDocument(doc);
    }
    
    
}
