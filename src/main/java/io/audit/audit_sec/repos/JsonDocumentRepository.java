package io.audit.audit_sec.repos;

// JsonDocumentRepository.java

import org.springframework.data.mongodb.repository.MongoRepository;
import  io.audit.audit_sec.domain.JsonDocument;
public interface JsonDocumentRepository extends MongoRepository<JsonDocument, String> {
}
