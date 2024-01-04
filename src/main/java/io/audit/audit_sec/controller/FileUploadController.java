package io.audit.audit_sec.controller;
import  io.audit.audit_sec.service.JsonStorageService;
// FileUploadController.java

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
@RequestMapping("/api")
public class FileUploadController {

    @Autowired
    private JsonStorageService jsonStorageService;

    @PostMapping("/upload/excel-and-json")
    public ResponseEntity<String> handleExcelAndJsonUpload(@RequestParam("file") MultipartFile file) {
        try {
            // Process and store JSON data
           throw new IOException() ;

            //return ResponseEntity.ok("File and JSON data uploaded successfully");
        } catch (IOException e) {

            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }
}
