package io.audit.audit_sec.service;
import  io.audit.audit_sec.repos.JsonDocumentRepository;
import  io.audit.audit_sec.domain.JsonDocument;

// JsonStorageService.java

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class JsonStorageService {

    @Autowired
    private JsonDocumentRepository jsonDocumentRepository;

    public void storeJson(List<List<Object>> jsonData) {
        // Convert jsonData to a JSON string (you may use a library like Jackson ObjectMapper for complex structures)
        String jsonString = convertToJson(jsonData);

        // Save JSON data to MongoDB
        JsonDocument jsonDocument = new JsonDocument();
        jsonDocument.setJsonContent(jsonString);
        jsonDocumentRepository.save(jsonDocument);
    }

    private String convertToJson(List<List<Object>> jsonData) {
        // Implement your conversion logic based on your JSON structure
        // For simplicity, assuming a basic structure where each row is an object
        StringBuilder jsonBuilder = new StringBuilder("[");
        for (List<Object> row : jsonData) {
            jsonBuilder.append("{");
            for (int i = 0; i < row.size(); i++) {
                jsonBuilder.append("\"column" + i + "\":\"" + row.get(i) + "\"");
                if (i < row.size() - 1) {
                    jsonBuilder.append(",");
                }
            }
            jsonBuilder.append("}");
            if (row != jsonData.get(jsonData.size() - 1)) {
                jsonBuilder.append(",");
            }
        }
        jsonBuilder.append("]");
        return jsonBuilder.toString();
    }
}
