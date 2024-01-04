package io.audit.audit_sec.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "GenericEntity")
public class GenericEntity {

    @Id
    private String id;

    private org.bson.Document jsonDocument;

    // Getter and Setter methods
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public org.bson.Document getJsonDocument() {
        return jsonDocument;
    }

    public void setJsonDocument(org.bson.Document jsonDocument) {
        this.jsonDocument = jsonDocument;
    }
}
