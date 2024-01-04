package io.audit.audit_sec.domain;

// JsonDocument.java

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "json_data")
public class JsonDocument {

    @Id
    private String id;
    private String jsonContent;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
// Getters and setters
public String getJsonContent() {
    return jsonContent;
}

    public void setJsonContent(String jsonContent) {
        this.jsonContent = jsonContent;
    }
}
