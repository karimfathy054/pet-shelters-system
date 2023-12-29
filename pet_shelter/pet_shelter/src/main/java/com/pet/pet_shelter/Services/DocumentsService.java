package com.pet.pet_shelter.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pet.pet_shelter.DAOs.DocumentDao;
import com.pet.pet_shelter.DTOs.Document;

@Service
public class DocumentsService {
    @Autowired
    DocumentDao docDao;

    public boolean addDocument(Document document){
        if(docDao.addDoc(document) == 1) return true;
        return false;
    }

    public List<Document> getPetsDocuments(long petId){
        return docDao.getDocumentsForPet(petId);
    }

}
