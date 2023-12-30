package com.pet.pet_shelter.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pet.pet_shelter.DTOs.Document;
import com.pet.pet_shelter.Services.DocumentsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/documents")
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
