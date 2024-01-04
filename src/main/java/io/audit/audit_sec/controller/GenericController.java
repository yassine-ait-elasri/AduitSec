package io.audit.audit_sec.controller;

// GenericController.java
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import  io.audit.audit_sec.domain.GenericEntity;
import io.audit.audit_sec.repos.GenericEntityRepository;
import java.util.List;

import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/api")
public class GenericController {

    @Autowired
    private GenericEntityRepository genericEntityRepository;

    @PostMapping("/store-json")
    public void storeJson(@RequestBody List<Document> jsonDocuments) {
        try{
            for (Document document : jsonDocuments) {
                GenericEntity genericEntity = new GenericEntity();
                    genericEntity.setJsonDocument(document);
                genericEntityRepository.save(genericEntity);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
